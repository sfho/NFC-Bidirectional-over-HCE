package edu.dce.nfc.libhce;

import android.app.Activity;
import android.nfc.NfcAdapter;
import android.util.Log;

import edu.dce.nfc.libhce.reader.CardReader;

/**
 * Created by championswimmer on 5/9/14.
 */
public class ReaderModeActivity extends Activity {

    public static final String TAG = "libHCE-ReaderModeActivity";
    public static int READER_FLAGS =
            NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK;

    private CardReader mCardReader;

    public ReaderModeActivity(CardReader.ReadCallBack ac) {
        mCardReader = new CardReader(ac);
    }

    /**
     * Method to start the reader mode.
     *
     * If reader mode isn't enabled, devices wont interact, even when tapped together.
     * Reader mode should be enabled before tapping to be able to detect the other device.
     */
    protected void enableReaderMode() {
        Log.i(TAG, "Enabling reader mode");

        NfcAdapter nfc = NfcAdapter.getDefaultAdapter(this);
        if (nfc != null) {
            nfc.enableReaderMode(this, mCardReader, READER_FLAGS, null);
        }
    }

    /**
     * Method to stop reader mode.
     *
     * Advisable to free up the reader mode when leaving the activity.
     * If reader mode is engaged, other NFC functions like Beam and other Wallet like
     * apps wont function, so always close reader mode safely before leaving the app.
     */
    protected void disableReaderMode() {
        Log.i(TAG, "Disabling reader mode");
        NfcAdapter nfc = NfcAdapter.getDefaultAdapter(this);
        if (nfc != null) {
            nfc.disableReaderMode(this);
        }
    }
}
