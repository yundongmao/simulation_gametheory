package GAME_THEORY;

public class GameWorker implements Runnable {
    private Thread t;
    private String threadName;
    public GameWorker(String _name){
        threadName = _name;
    }
    @Override
    public void run() {
//        Setting setting = SettingHandle.readSetting();
        for (int i = 0; i < 100; i++) {
            SettingHandle.readSetting();
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread(this,threadName);
            t.start();
        }
    }
}
