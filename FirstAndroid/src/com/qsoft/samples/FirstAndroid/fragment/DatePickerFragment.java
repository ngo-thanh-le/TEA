package com.qsoft.samples.FirstAndroid.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import com.qsoft.samples.FirstAndroid.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * User: Le
 * Date: 10/18/13
 */
public class DatePickerFragment extends DialogFragment
{
    public static final String EXTRA_DATE =
            "com.bignerdranch.android.criminalintent.date";
    private Date mDate;

    public static DatePickerFragment newInstance(Date date)
    {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, date);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        mDate = (Date) getArguments().getSerializable(EXTRA_DATE);
// Create a Calendar to get the year, month, and day
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.dialog_date, null);

        DatePicker datePicker = (DatePicker) v.findViewById(R.id.dialog_date_datePicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener()
        {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                mDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
                getArguments().putSerializable(EXTRA_DATE, mDate);
            }
        });

        return new AlertDialog.Builder(
                getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title).setPositiveButton(android.R.string.ok, null)
                .setPositiveButton(
        android.R.string.ok,
        new DialogInterface.OnClickListener()
        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                sendResult(Activity.RESULT_OK);
                            }
                        })
                .create();
    }

    private void sendResult(int resultCode)
    {
        if (getTargetFragment() == null)
        {
            return;
        }
        Intent i = new Intent();
        i.putExtra(EXTRA_DATE, mDate);
        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, i);
    }
}
