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
        while (true) {
            Setting setting = SettingHandle.readSetting();
            if(setting == null){
                    break;
            }
            setting.runTest();
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread(this,threadName);
            t.start();
        }
    }
}
