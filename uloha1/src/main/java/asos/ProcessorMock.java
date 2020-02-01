package asos;

public class ProcessorMock implements IProcessor {

    private IConsumer messageConsumer;

    private final ISource messageSource;

    public ProcessorMock(ISource messageSource) {
        this.messageSource = messageSource;
    }

    public IConsumer getMessageConsumer() {
        return messageConsumer;
    }

    public void setMessageConsumer(IConsumer messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    @Override
    public void process() {
        while (true) {
            String msg = messageSource.nextMessage();
            if (msg == null) {
                break;
            }
            messageConsumer.putMessage(msg);
        };
    }

}
