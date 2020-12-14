package com.hussein.eventdriven;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: AsyncEventMessageDispatcherTest</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/14 3:42 PM
 */
public class AsyncEventMessageDispatcherTest {

    static class InputEventMessage extends EventMessage {

        private final int x;

        private final int y;

        public InputEventMessage(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }


    static class ResultEventMessage extends EventMessage {

        private final int result;

        public ResultEventMessage(int result) {
            this.result = result;
        }

        public int getResult() {
            return result;
        }
    }

    static class AsyncInputEventMessageHandler extends AsyncChannel {

        private final AsyncEventMessageDispatcher dispatcher;

        public AsyncInputEventMessageHandler(AsyncEventMessageDispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        @Override
        protected void handler(Message message) {
            InputEventMessage msg = (InputEventMessage) message;
            System.out.printf("X=[%d],y=[%d]\n", msg.getX(), msg.getY());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int result = msg.getX() + msg.getY();
            dispatcher.dispatch(new ResultEventMessage(result));
        }
    }

    static class AsyncResultEventMessageHandler extends AsyncChannel {

        @Override
        protected void handler(Message message) {
            ResultEventMessage result = (ResultEventMessage) message;
            System.out.println("the result is:" + result.getResult());
        }
    }

    public static void main(String[] args) {
        AsyncEventMessageDispatcher dispatcher = new AsyncEventMessageDispatcher();
        dispatcher.register(InputEventMessage.class, new AsyncInputEventMessageHandler(dispatcher));
        dispatcher.register(ResultEventMessage.class, new AsyncResultEventMessageHandler());
        dispatcher.dispatch(new InputEventMessage(1, 2));
        System.out.println("main end..");
    }
}
