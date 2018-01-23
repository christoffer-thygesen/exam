package yellow.sausages.com.exam;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.tech.NfcF;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class ExternalHardwareActivity extends AppCompatActivity {

    //region NFC STUFF
    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    private IntentFilter[] intentFiltersArray;
    private String[][] techListsArray;
    //endregion

    private TextView nfcText;
    private BluetoothHeadset bluetoothHeadset;
    private BluetoothAdapter bluetoothAdapter = null;
    private Button btButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_hardware);

        nfcText = findViewById(R.id.nfcText);
        btButton = findViewById(R.id.btButton);

        PackageManager pm = this.getPackageManager();
        boolean hasBluetooth = pm.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH);


        if(hasBluetooth) {
            Toast.makeText(this, "I do", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(this, "I don't", Toast.LENGTH_SHORT).show();
        }
        //Log.d("DEBUG_", "has it: " + hasBluetooth);

//        final BluetoothManager mbluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
//        if(mbluetoothManager == null) {
//            Toast.makeText(this, "LOL", Toast.LENGTH_SHORT).show();
//            //finish();
//        } else {
//            Toast.makeText(this, "LEL", Toast.LENGTH_SHORT).show();
//            //finish();
//        }
//
//        btButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bluetoothAdapter = mbluetoothManager.getAdapter();
//
//                if (bluetoothAdapter == null) {
//                    Log.d("DEBUG_BLUE", "blue: " + bluetoothAdapter);
//                    // Stop here, we definitely need NFC
//                    nfcText.setText("wtf");
//                }
//            }
//        });


//        bluetoothAdapter = mbluetoothManager.getAdapter();
//
//        if (bluetoothAdapter == null) {
//            Log.d("DEBUG_BLUE", "blue: " + bluetoothAdapter);
//            // Stop here, we definitely need NFC
//            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
//            finish();
//        }
//
//        BluetoothProfile.ServiceListener mProfileListener = new BluetoothProfile.ServiceListener() {
//            public void onServiceConnected(int profile, BluetoothProfile proxy) {
//                if (profile == BluetoothProfile.HEADSET) {
//                    bluetoothHeadset = (BluetoothHeadset) proxy;
//                }
//            }
//            public void onServiceDisconnected(int profile) {
//                if (profile == BluetoothProfile.HEADSET) {
//                    bluetoothHeadset = null;
//                }
//            }
//        };

        //region NFC STUFF, DON'T BOTHER
//        nfcText = findViewById(R.id.nfcText);
//        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
//        if (nfcAdapter == null) {
//            // Stop here, we definitely need NFC
//            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
//            finish();
//        }
//
//        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass())
//                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
//
//        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
//        IntentFilter ndef2 = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
//        try {
//
//            ndef.addDataType("*/*");
//        } catch (IntentFilter.MalformedMimeTypeException e) {
//            throw new RuntimeException("fail", e);
//        }
//        intentFiltersArray = new IntentFilter[]{ndef, ndef2};
//        techListsArray = new String[][]{new String[]{NfcF.class.getName()}};
        //endregion
    }

    //region MORE NFC STUFF
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(pendingIntent == null) {
//            Log.d("DEBUG_NULL", "is null");
//        } else {
//            Log.d("DEBUG_NULL", "is not null");
//        }
//        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, techListsArray);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        nfcAdapter.disableForegroundDispatch(this);
//    }
//
//    public String readNFC(Intent intent) {
//
//        String string = "EMPTY";
//
//        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
//
//            Parcelable[] rawMessages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
//
//            for (Parcelable rawMessage : rawMessages) {
//
//                NdefMessage message = (NdefMessage) rawMessage;
//                NdefRecord[] records = message.getRecords();
//
//                for (NdefRecord record : records) {
//
//                    if (record.getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(record.getType(), NdefRecord.RTD_TEXT)) {
//
//                        byte[] payload = record.getPayload();
//                        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
//                        int languageCodeLength = payload[0] & 0063;
//
//                        try {
//
//                            string = new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
//
//                        } catch (UnsupportedEncodingException e) {
//
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//
//        return string;
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        nfcText.setText("Hey, I got something!");
//    }
    //endregion
}
