package com.hussein.observe;

/**
 * <p>Title: ObservableThread</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/1 11:11 AM
 */
public class ObservableThread<T> extends Thread implements Observable {

    private TaskLifeCycle<T> lifeCycle;

    private Cycle cycle;

    private Task<T> task;

    public ObservableThread(Task<T> task) {
        this(new TaskLifeCycle.EmptyTaskLifeCycle<>(), task);
    }

    public ObservableThread(TaskLifeCycle<T> lifeCycle, Task<T> task) {
        super();
        this.lifeCycle = lifeCycle;
        this.task = task;
    }

    @Override
    public void run() {
        this.update(Cycle.STARTED, null, null);
        try {
            this.update(Cycle.RUNNING, null, null);
            T result = this.task.call();
            this.update(Cycle.DONE, result, null);
        } catch (Exception e) {
            this.update(Cycle.ERROR, null, e);
        }
    }

    private void update(Cycle cycle, T result, Exception e) {
        switch (cycle) {
            case STARTED:
                lifeCycle.onStart(Thread.currentThread());
                break;
            case RUNNING:
                lifeCycle.onRunning(Thread.currentThread());
                break;
            case DONE:
                lifeCycle.onFinish(Thread.currentThread(), result);
                break;
            case ERROR:
                lifeCycle.onError(Thread.currentThread(), e);
                break;
        }
    }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }
}
