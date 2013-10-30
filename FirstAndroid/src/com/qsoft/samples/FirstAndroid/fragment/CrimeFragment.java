package com.qsoft.samples.FirstAndroid.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import com.qsoft.samples.FirstAndroid.*;
import com.qsoft.samples.FirstAndroid.model.Crime;
import com.qsoft.samples.FirstAndroid.model.CrimeLab;
import com.qsoft.samples.FirstAndroid.utils.LogUtils;

import java.util.Date;
import java.util.UUID;

/**
 * User: Le
 * Date: 10/14/13
 */
@ViewMapping(R.layout.fragment_crime)
public class CrimeFragment extends SuperFragment
{
    public static final String EXTRA_CRIME_ID = "crime_id";
    @ComponentMapping(R.id.crime_title)
    private EditText mTitleField;

    @ComponentMapping(R.id.crime_date)
    private Button mDateButton;

    @ComponentMapping(R.id.crime_solved)
    private CheckBox mSolvedCheckBox;

    private static final String DIALOG_DATE = "date";
    private static final int REQUEST_DATE = 0;

    private Crime mCrime;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        UUID crimeId = (UUID)getActivity().getIntent()
//                .getSerializableExtra(EXTRA_CRIME_ID);
        UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
        if (mCrime == null)
        {
            mCrime = new Crime();
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
        mTitleField.addTextChangedListener(new TextWatcher()
        {
            public void onTextChanged(CharSequence c, int start, int before, int count)
            {
                mCrime.setTitle(c.toString());
                LogUtils.debugLog(this, "Hey");
            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after)
            {
// This space intentionally left blank
            }

            public void afterTextChanged(Editable c)
            {
// This one too
            }
        });

        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                FragmentManager fm = getActivity()
                        .getFragmentManager();
//                DatePickerFragment dialog = new DatePickerFragment();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(fm, DIALOG_DATE);
            }
        });

        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                mCrime.setSolved(isChecked);
            }
        });
    }

    @Override
    public void onAfterCreateView()
    {
        mTitleField.setText(mCrime.getTitle());
        updateDate();
        mSolvedCheckBox.setChecked(mCrime.isSolved());
    }

    public static CrimeFragment newInstance(UUID crimeId)
    {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID, crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent i)
    {
        if (resultCode != Activity.RESULT_OK)
        {
            return;
        }
        if (requestCode == REQUEST_DATE)
        {
            Date date = (Date) i
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        }
    }

    private void updateDate()
    {
        mDateButton.setText(mCrime.getDate().toString());
    }
}
