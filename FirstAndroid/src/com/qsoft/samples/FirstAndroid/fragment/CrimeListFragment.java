package com.qsoft.samples.FirstAndroid.fragment;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import com.qsoft.samples.FirstAndroid.CrimePagerActivity;
import com.qsoft.samples.FirstAndroid.R;
import com.qsoft.samples.FirstAndroid.model.Crime;
import com.qsoft.samples.FirstAndroid.model.CrimeLab;

import java.util.ArrayList;

/**
 * User: Le
 * Date: 10/14/13
 */
public class CrimeListFragment extends ListFragment
{
    private static final int REQUEST_CRIME = 1;
    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
        mCrimes = CrimeLab.get(getActivity()).getCrimes();
//        GUIUtils.mapUIComponents(this.getView());

//        ArrayAdapter<Crime> adapter =
//                new ArrayAdapter<Crime>(getActivity(),
//                        android.R.layout.simple_list_item_1,
//                        mCrimes);
        CrimeAdapter adapter = new CrimeAdapter(mCrimes);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long size)
    {
//        Crime c = (Crime) (getListAdapter()).getItem(position);
        Crime c = ((CrimeAdapter) getListAdapter()).getItem(position);
        Log.d("CrimeListFragment", c.getTitle() + " was clicked");

        // Start CrimeActivity
//        Intent i = new Intent(getActivity(), CrimeActivity.class);
        // Start CrimePagerActivity with this crime
        Intent i = new Intent(getActivity(), CrimePagerActivity.class);
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
        startActivityForResult(i, REQUEST_CRIME);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent i)
    {
        if (requestCode == REQUEST_CRIME)
        {
// Handle result
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        ((CrimeAdapter) getListAdapter()).notifyDataSetChanged();
    }

    private class CrimeAdapter extends ArrayAdapter<Crime>
    {
        public CrimeAdapter(ArrayList<Crime> crimes)
        {
            super(getActivity(), 0, crimes);
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            // If we weren't given a view, inflate one
            if (convertView == null)
            {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_crime, null);
            }
            // Configure the view for this Crime
            Crime c = getItem(position);
            TextView titleTextView =
                    (TextView) convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getTitle());
            TextView dateTextView =
                    (TextView) convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(c.getDate().toString());
            CheckBox solvedCheckBox =
                    (CheckBox) convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.isSolved());
            return convertView;
        }
    }
}
