import java.util.concurrent.LinkedBlockingQueue;

public class CustomerManagementModule extends Module{
    //private final int LAMBDA = 35;
    private int kConnections;
    private int rejectedConnections;
    private int currentConnections;

    public CustomerManagementModule(int kConnections){
        this.kConnections = kConnections;
        queue = new LinkedBlockingQueue<>();
        timeQueue = new LinkedBlockingQueue<>();
        rejectedConnections = 0;
        currentConnections = 0;
        hasBeenInQueue = 0;
    }

    public int getRejectedConnections() {
        return rejectedConnections;
    }

    public int getCurrentConnections() {
        return currentConnections;
    }

    public void setRejectedConnections(int rejectedConnections) {
        this.rejectedConnections = rejectedConnections;
    }


    public boolean wasInserted(Query query){
        if(queue.size() < kConnections) {
            super.insertQuery(query);
            return true;
        } else
            rejectedConnections++;

        return false;


    }

    @Override
    public float getNextExitTime(){
        return DistributionGenerator.getNextRandomValueByNormal((float) 0.01 , (float)0.05);
    }
}
