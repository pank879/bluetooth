package bluetooth.sdsa.samsung.com.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private static TextView information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        information = (TextView)findViewById(R.id.information);
        information.setText("");
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        this.registerReceiver(mReceiver, filter);
    }

    //The BroadcastReceiver that listens for bluetooth broadcasts
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            String isConntected = "";
            if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
                isConntected = "YES";
            } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                isConntected = "NO";
            }
            information.append("\nTime : "+ Calendar.getInstance().getTime()+", Device : "+device.getName()+", Mac : "+device.getAddress()+", State : "+device.getBondState()+",Device Connection : "+isConntected);
        }
    };
}
