package asos;

public class ConsumerMock implements IConsumer {

    @Override
    public void putMessage(String m) {
        System.out.println("" + m);
    }
   
}
