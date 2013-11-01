Section 04 - Activity & Fragment State Management

Overview:
    This section guide you how to manage state of activities/fragments. The most common cases are configuration
        changes, which make an Activity re-create. We want to maintain several attributes and progresses that
        running.
    Here we only mention about maintain state of Activity/Fragment on Application Scope, that mean the Application
        should be remain un-touch (not closed), otherwise, no state is retained.
    The first part of this section is try to explain the way we maintain state of Activity's properties, then
        the Activity's long running task. We will go through AsyncTask, Fragment Retain then Threading model to
        maintain the long running task work. Check the appropriate situations that you can use each one by yourself.
    Hint: Fragment Retain is used to maintain the state of long time running task from previous section 03, build
        within our framework.
Guideline:
    OriginalActivity - This activity show 3 buttons that using GenericStartActivityCommand to start following
        activities.
    BeingDestroyActivity - This activity contain a state field, that will be destroy if we change the configuration
        or destroy the activity.
    RetainedWithinActivity - This activity retained the state field within the activity, its state will be destroyed
        if the activity is destroy (simply by pressing back button)
    RetainedInApplicationActivity - This activity retained the state until the application is destroy. Its states are
        stored within the application by a static field in StateUtils.
    AsyncTaskProgressActivity - This activity maintain state of an asynctask within activity lifecycle, it used
        @Override onRetainNonConfigurationInstance() which is deprecated.
        restoreAsyncTask() is called each time the activity is reload (onCreate())
        onRetainNonConfigurationInstance() limit store only one object at a time and not so useful.
    FragmentRetainInstanceActivity - This activity use Fragment Retain way, which setRetainInstance(true) for
        RetainedFragment.
        This fragment then used to maintain the state of progress running. Understand that FragmentManager is
        the one that do manage fragments and being keep through until Activity is destroyed.

    Note: If you start FragmentRetainInstanceActivity from OriginalActivity, press Back button will destroy the
        activity, so the retained fragment attach to that activity.
    Note: AsyncTaskProgressActivity start a dialog that prevent user to press Back button.

