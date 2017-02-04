import java.util.concurrent.LinkedBlockingQueue;

public class QueryProcessingModule extends Module{
    private int nAvailableProcesses;
    private int currentProcesses;
    public QueryProcessingModule(int nAvailableProcesses){
        queue = new LinkedBlockingQueue<>();
        this.nAvailableProcesses = nAvailableProcesses;
        currentProcesses = 0;
    }

    public int getnAvailableProcesses() {
        return nAvailableProcesses;
    }
}