Section 04 - Activity & Fragment State Management

Overview:
    This section guide you how to manage state of activities/fragments. The most common cases are configuration
        changes, which make an Activity re-create. We want to maintain several attributes and progresses that
        running.
    Here we only mention about maintain state of Activity/Fragment on Application Scope, that mean the Application
        should be remain un-touch (not closed), otherwise, no state is retained.
Guideline:
    OriginalActivity - This activity show 3 buttons that using GenericStartActivityCommand to start following
        activities.
    BeingDestroyActivity - This activity contain a state field, that will be destroy if we change the configuration
        or destroy the activity.
    RetainedWithinActivity - This activity retained the state field within the activity, its state will be destroyed
        if the activity is destroy (simply by pressing back button)
    RetainedInApplicationActivity - This activity retained the state until the application is destroy. Its states are
        stored within the application by a static field in StateUtils.
