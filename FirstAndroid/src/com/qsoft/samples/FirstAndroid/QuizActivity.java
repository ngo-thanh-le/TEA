package com.qsoft.samples.FirstAndroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.qsoft.samples.FirstAndroid.utils.GUIUtils;
import com.qsoft.samples.FirstAndroid.utils.StateUtils;

import static com.qsoft.samples.FirstAndroid.CheatActivity.IS_CHEATED;

/**
 * User: Le
 * Date: 10/9/13
 */
//@ComponentMapping(R.layout.activity_quiz)
public class QuizActivity extends SuperActivity
{
    private final String TAG = this.getClass().getName();

    @ComponentMapping(R.id.true_button)
    private Button mTrueButton;

    @ComponentMapping(R.id.false_button)
    private Button mFalseButton;

    @ComponentMapping(R.id.next_button)
    private Button mNextButton;

    @ComponentMapping(R.id.previous_button)
    private Button mPreviousButton;

    @ComponentMapping(R.id.previous_button_image)
    private ImageButton mPreviousButtonImage;

    @ComponentMapping(R.id.question_text_view)
    private TextView mQuestionTextView;

    private TrueFalseQuestion[] mQuestionBank = new TrueFalseQuestion[]{
            new TrueFalseQuestion(R.string.question_oceans, true),
            new TrueFalseQuestion(R.string.question_mideast, false),
            new TrueFalseQuestion(R.string.question_africa, false),
            new TrueFalseQuestion(R.string.question_americas, true),
            new TrueFalseQuestion(R.string.question_asia, true),
    };

    @SaveActivityState
    private int mCurrentIndex = 0;
    public static final String EXTRA_ANSWER_IS_TRUE =
            "com.bignerdranch.android.geoquiz.answer_is_true";
    @SaveActivityState
    private boolean mIsCheater = false;

    public void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate(Bundle) called");
        StateUtils.loadState(this, savedInstanceState);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        GUIUtils.mapUIComponents(null, this);

        // This return actual created button
        mTrueButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkAnswer(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                checkAnswer(false);
            }
        });

        updateQuestion();

        mNextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nextQuestion();
            }
        });
        mQuestionTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nextQuestion();
            }
        });
        mPreviousButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                previousQuestion();
            }
        });

        mPreviousButtonImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                previousQuestion();
            }
        });

        findViewById(R.id.next_button_image).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nextQuestion();
            }
        });

        findViewById(R.id.cheat_button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Start CheatActivity
                Intent i = new Intent(QuizActivity.this, CheatActivity.class);
                i.putExtra(EXTRA_ANSWER_IS_TRUE, mQuestionBank[mCurrentIndex].isTrueQuestion());
//                startActivity(i);
                startActivityForResult(i, 0);
//                setContentView(R.layout.activity_cheat);
            }
        });
    }

    private void nextQuestion()
    {
        mCurrentIndex = mCurrentIndex < mQuestionBank.length - 1 ? (mCurrentIndex + 1) : mCurrentIndex;
        updateQuestion();
    }

    private void previousQuestion()
    {

        mCurrentIndex = mCurrentIndex > 0 ? (mCurrentIndex - 1) : mCurrentIndex;
        updateQuestion();
    }

    private void updateQuestion()
    {
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue)
    {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
        int messageResId = 0;
        if (mIsCheater)
        {
            messageResId = R.string.judgment_toast;
        }
        else
        {
            if (userPressedTrue == answerIsTrue)
            {
                messageResId = R.string.correct_toast;
            }
            else
            {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (data == null)
        {
            return;
        }
        mIsCheater = data.getBooleanExtra(IS_CHEATED, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}