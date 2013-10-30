package com.qsoft.eip.common.utils;

import android.view.View;
import android.widget.Toast;
import com.qsoft.eip.activity.SuperActivity;
import com.qsoft.eip.activity.SuperFragment;
import com.qsoft.eip.common.IActivityCommand;
import com.qsoft.eip.common.IFragmentCommand;
import com.qsoft.eip.common.IModelContainer;
import com.qsoft.eip.common.annotation.Command;
import com.qsoft.eip.common.annotation.Commands;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Le
 * Date: 10/16/13
 */
public class CommandUtils
{
    public static void mapCommands(final SuperActivity activity)
    {
        for (Field member : ClassUtils.getAllFields(activity.getClass()))
        {
            if (!member.isAccessible())
            {
                member.setAccessible(true);
            }
            try
            {
                if (member.isAnnotationPresent(Command.class))
                {
                    if (!View.class.isAssignableFrom(member.getType()))
                    {
                        LogUtils.debugLog(CommandUtils.class, "Mapping event for non-view type is not support.");
                        continue;
                    }
                    setupCommandsForActivity(activity, member);
                }
                else if (member.isAnnotationPresent(Commands.class))
                {
                    final Map<Class<?>, List<Command>> eventCommandsMap = new HashMap<Class<?>, List<Command>>();
                    Commands commands = member.getAnnotation(Commands.class);
                    for (final Command command : commands.value())
                    {
                        if (command.event() == View.OnClickListener.class)
                        {
                            if (eventCommandsMap.get(View.OnClickListener.class) == null)
                            {
                                eventCommandsMap.put(View.OnClickListener.class, new ArrayList<Command>());
                            }
                            eventCommandsMap.get(View.OnClickListener.class).add(command);
                        }
                    }
                    ((View) member.get(activity)).setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            for (Command command : eventCommandsMap.get(View.OnClickListener.class))
                            {
                                IActivityCommand commandInstance = null;
                                try
                                {
                                    commandInstance = (IActivityCommand) command.value().newInstance();
                                    commandInstance.execute(activity, activity instanceof IModelContainer ? activity : null, command.parameters());
                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(activity, "An error happened. Check log for more details.", Toast.LENGTH_SHORT).show();
                                    LogUtils.debugLog(commandInstance, e.getMessage());
                                }
                            }
                        }
                    });
                }
            }
            catch (Exception e)
            {
                LogUtils.debugLog(activity, e.getMessage(), e);
            }
        }
    }

    public static void mapCommands(final SuperFragment fragment)
    {
        for (Field member : ClassUtils.getAllFields(fragment.getClass()))
        {
            if (!member.isAccessible())
            {
                member.setAccessible(true);
            }
            try
            {
                if (member.isAnnotationPresent(Command.class))
                {
                    if (!View.class.isAssignableFrom(member.getType()))
                    {
                        LogUtils.debugLog(CommandUtils.class, "Mapping event for non-view type is not support.");
                        continue;
                    }
                    setupCommandsForFragment(fragment, member);
                }
                else if (member.isAnnotationPresent(Commands.class))
                {
                    final Map<Class<?>, List<Command>> eventCommandsMap = new HashMap<Class<?>, List<Command>>();
                    Commands commands = member.getAnnotation(Commands.class);
                    for (final Command command : commands.value())
                    {
                        if (command.event() == View.OnClickListener.class)
                        {
                            if (eventCommandsMap.get(View.OnClickListener.class) == null)
                            {
                                eventCommandsMap.put(View.OnClickListener.class, new ArrayList<Command>());
                            }
                            eventCommandsMap.get(View.OnClickListener.class).add(command);

                        }
                    }
                    ((View) member.get(fragment)).setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            for (Command command : eventCommandsMap.get(View.OnClickListener.class))
                            {
                                IFragmentCommand commandInstance = null;
                                try
                                {
                                    commandInstance = (IFragmentCommand) command.value().newInstance();
                                    commandInstance.execute(fragment, fragment instanceof IModelContainer ? fragment : null, command.parameters());
                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(fragment.getActivity(), "An error happened. Check log for more details.", Toast.LENGTH_SHORT).show();
                                    LogUtils.debugLog(commandInstance, e.getMessage());
                                }
                            }
                        }
                    });
                }
            }
            catch (Exception e)
            {
                LogUtils.debugLog(fragment, e.getMessage(), e);
            }
        }
    }

    private static void setupCommandsForActivity(final SuperActivity activity, Field member) throws InstantiationException, IllegalAccessException
    {
        final Command command = member.getAnnotation(Command.class);
        final IActivityCommand commandInstance = (IActivityCommand) command.value().newInstance();
        if (command.event() == View.OnClickListener.class)
        {
            ((View) member.get(activity)).setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    try
                    {
                        commandInstance.execute(activity, activity instanceof IModelContainer ? activity : null, command.parameters());
                    }
                    catch (SQLException e)
                    {
                        Toast.makeText(activity, "An error happened. Check log for more details.", Toast.LENGTH_SHORT).show();
                        LogUtils.debugLog(commandInstance, e.getMessage());
                    }

                    // AsyncTask test
                }
            });
        }
    }

    private static void setupCommandsForFragment(final SuperFragment fragment, Field member) throws InstantiationException, IllegalAccessException
    {
        final Command command = member.getAnnotation(Command.class);
        final IFragmentCommand commandInstance = (IFragmentCommand) command.value().newInstance();
        if (command.event() == View.OnClickListener.class)
        {
            ((View) member.get(fragment)).setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    try
                    {
                        commandInstance.execute(fragment, fragment instanceof IModelContainer ? fragment : null, command.parameters());
                    }
                    catch (SQLException e)
                    {
                        Toast.makeText(fragment.getActivity(), "An error happened. Check log for more details.", Toast.LENGTH_SHORT).show();
                        LogUtils.debugLog(commandInstance, e.getMessage());
                    }

                    // AsyncTask test
                }
            });
        }
    }
}
