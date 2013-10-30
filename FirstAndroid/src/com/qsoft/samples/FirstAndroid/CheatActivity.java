package com.qsoft.samples.FirstAndroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.qsoft.samples.FirstAndroid.QuizActivity.EXTRA_ANSWER_IS_TRUE;

/**
 * Created with IntelliJ IDEA.
 * User: Le
 * Date: 10/11/13
 * Time: 11:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class CheatActivity extends Activity
{
    public static final String IS_CHEATED = "IS_CHEATED";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, true);

        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
        mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent data = new Intent();
                data.putExtra(IS_CHEATED, true);
                setResult(Activity.RESULT_OK, data);

                if (mAnswerIsTrue)
                {
                    mAnswerTextView.setText(R.string.true_button);
                }
                else
                {
                    mAnswerTextView.setText(R.string.false_button);
                }
            }
        });

    }
}
