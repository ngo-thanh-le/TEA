Section 03 - Command Framework (cont.) - Async command

Overview:
    Continue on previous section, this section enhanced command with async commands. We will demo the combination
        usage of sync and async commands.
Guideline:
    HeadlessContainerActivity - We could use an headless activity to store a fragment. This is used for
        MultipleProgressFragment01/MultipleProgressFragment02 as container.
    MultipleProgressFragment01 - This fragment contains 8 buttons and 8 progress bar that start individual retained
        fragments, that keep running as long as the application is running.
    RetainedFragment - A generic fragment that allow to run Runnable object set. Also support optional (TODO)
        progress bar setup, so it can report back the progress running.
    MultipleProgressFragment02 - This fragment contains 3 buttons and 3 progress bar, one of button have multiple
        commands - both sync and async command.
    MultipleProgressActivity01/02 - The concrete class for headless activity, which implement the return fragment
        method.
