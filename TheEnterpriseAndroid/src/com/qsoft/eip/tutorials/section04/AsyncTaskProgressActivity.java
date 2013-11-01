package com.qsoft.eip.tutorials.section04;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.qsoft.eip.R;
import com.qsoft.eip.common.SuperActivity;
import com.qsoft.eip.common.annotation.GUIMapping;
import com.qsoft.eip.common.annotation.ViewMapping;
import com.qsoft.eip.common.utils.LogUtils;

/**
 * User: Le
 * Date: 10/28/13
 */
@ViewMapping(R.layout.activity_async_task)
public class AsyncTaskProgressActivity extends SuperActivity
{
    @GUIMapping(R.id.readWebpage)
    private Button buttonReadWebpage;

    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    private volatile ProgressDialog mProgressDialog;

    DownloadZipFileTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        buttonReadWebpage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                asyncTask = new DownloadZipFileTask();
                asyncTask.execute("");
            }
        });

        restoreAsyncTask();
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch (id)
        {
            case DIALOG_DOWNLOAD_PROGRESS:
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setMessage("waiting 20 seconds..");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
                return mProgressDialog;
            default:
                return null;
        }
    }

    @Override
    public Object onRetainNonConfigurationInstance()
    {
        if (mProgressDialog != null)
        {
            mProgressDialog.dismiss();
        }
        if (asyncTask != null)
        {
            return asyncTask;
        }
        return super.onRetainNonConfigurationInstance();
    }

    private void restoreAsyncTask()
    {
//        mProgressDialog = new ProgressDialog(AsyncTaskProgressActivity.this);
        if (getLastNonConfigurationInstance() != null)
        {
            asyncTask = (DownloadZipFileTask) getLastNonConfigurationInstance();
            if (asyncTask != null)
            {
                if (!(asyncTask.getStatus()
                        .equals(AsyncTask.Status.FINISHED)))
                {
//                    asyncTask.execute("");
                    LogUtils.debugLog(this, "Continue the job...");
                    asyncTask.parent = AsyncTaskProgressActivity.this;
                }
            }
        }
    }

    private class DownloadZipFileTask extends AsyncTask<String, String, String>
    {
        public AsyncTaskProgressActivity parent;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            showDialog(DIALOG_DOWNLOAD_PROGRESS);
            parent = AsyncTaskProgressActivity.this;
        }

        @Override
        protected String doInBackground(String... urls)
        {
            int progress = 0;
            for (; progress <= 100; progress++)
            {

                try
                {
                    Thread.sleep(200);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                publishProgress("" + progress);
            }
            return "Complete";
        }

        protected void onProgressUpdate(String... progress)
        {
            parent.mProgressDialog.setProgress(Integer.parseInt(progress[0]));
        }

        @Override
        protected void onPostExecute(String result)
        {
            parent.mProgressDialog.dismiss();
        }
    }
}
