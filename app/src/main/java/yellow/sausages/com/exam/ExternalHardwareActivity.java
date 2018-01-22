package yellow.sausages.com.exam;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class ExternalHardwareActivity extends AppCompatActivity implements NfcAdapter.ReaderCallback {

    private NfcAdapter nfcAdapter;
    private TextView nfcText;
    private PendingIntent pendingIntent;
    private IntentFilter[] intentFiltersArray;
    private String[][] techListsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_hardware);

        nfcText = findViewById(R.id.nfcText);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass())
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter ndef2 = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        try {

            ndef.addDataType("*/*");
        } catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException("fail", e);
        }
        intentFiltersArray = new IntentFilter[]{ndef, ndef2};
        techListsArray = new String[][]{new String[]{NfcF.class.getName()}};
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(pendingIntent == null) {
            Log.d("DEBUG_NULL", "is null");
        } else {
            Log.d("DEBUG_NULL", "is not null");
        }
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, techListsArray);
    }

    @Override
    protected void onPause() {
        super.onPause();
        nfcAdapter.disableForegroundDispatch(this);
    }

    public String readNFC(Intent intent) {

        String string = "EMPTY";

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {

            Parcelable[] rawMessages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            for (Parcelable rawMessage : rawMessages) {

                NdefMessage message = (NdefMessage) rawMessage;
                NdefRecord[] records = message.getRecords();

                for (NdefRecord record : records) {

                    if (record.getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(record.getType(), NdefRecord.RTD_TEXT)) {

                        byte[] payload = record.getPayload();
                        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
                        int languageCodeLength = payload[0] & 0063;

                        try {

                            string = new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);

                        } catch (UnsupportedEncodingException e) {

                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return string;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        nfcText.setText("Hey, I got something!");
    }

    @Override
    public void onTagDiscovered(Tag tag) {
        nfcText.setText("Hey, I got something!");
    }
}
