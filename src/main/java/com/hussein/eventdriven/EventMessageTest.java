package com.hussein.eventdriven;

/**
 * <p>Title: EventMessageTest</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/14 2:54 PM
 */
public class EventMessageTest {

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

    static class InputEventMessageHandler implements Channel<InputEventMessage> {

        private final EventMessageDispatcher dispatcher;

        public InputEventMessageHandler(EventMessageDispatcher dispatcher) {
            super();
            this.dispatcher = dispatcher;
        }

        @Override
        public void dispatch(InputEventMessage message) {
            System.out.printf("X=[%d],y=[%d]\n", message.getX(), message.getY());
            int result = message.getX() + message.getY();
            dispatcher.dispatch(new ResultEventMessage(result));
        }
    }

    static class ResultEventMessageHandler implements Channel<ResultEventMessage> {

        @Override
        public void dispatch(ResultEventMessage message) {
            System.out.println("the result is:" + message.getResult());
        }
    }

    public static void main(String[] args) {
        EventMessageDispatcher dispatcher = new EventMessageDispatcher();
        dispatcher.register(InputEventMessage.class, new InputEventMessageHandler(dispatcher));
        dispatcher.register(ResultEventMessage.class, new ResultEventMessageHandler());
        dispatcher.dispatch(new InputEventMessage(1, 2));
    }
}
