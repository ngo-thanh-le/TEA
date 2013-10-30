package com.qsoft.samples.FirstAndroid;

/**
 * Created with IntelliJ IDEA.
 * User: Le
 * Date: 10/9/13
 * Time: 9:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class TrueFalseQuestion
{
// ------------------------------ FIELDS ------------------------------

    private int mQuestion;
    private boolean mTrueQuestion;

// --------------------------- CONSTRUCTORS ---------------------------

    public TrueFalseQuestion(int question, boolean trueQuestion)
    {
        mQuestion = question;
        mTrueQuestion = trueQuestion;
    }

// -------------------------- OTHER METHODS --------------------------

    public int getQuestion()
    {
        return mQuestion;
    }

    public boolean isTrueQuestion()
    {
        return mTrueQuestion;
    }

    public void setQuestion(int mQuestion)
    {
        this.mQuestion = mQuestion;
    }

    public void setTrueQuestion(boolean mTrueQuestion)
    {
        this.mTrueQuestion = mTrueQuestion;
    }
}
