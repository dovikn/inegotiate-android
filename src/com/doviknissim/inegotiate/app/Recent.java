package com.doviknissim.inegotiate.app;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.sql.SQLException;
import java.util.ArrayList;
import org.codehaus.jackson.io.CharacterEscapes;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

public class Recent extends Activity {
    private ArrayList<OfferObject> offersItem;

    /* renamed from: com.doviknissim.inegotiate.app.Recent.1 */
    class C02001 implements OnClickListener {
        C02001() {
        }

        public void onClick(View v) {
            Recent.this.finish();
        }
    }

    /* renamed from: com.doviknissim.inegotiate.app.Recent.2 */
    class C02012 implements OnItemClickListener {
        private final /* synthetic */ ListView val$lv;

        C02012(ListView listView) {
            this.val$lv = listView;
        }

        public void onItemClick(AdapterView<?> adapterView, View arg1, int position, long id) {
            String offerId = ((OfferObject) this.val$lv.getItemAtPosition(position)).getId();
            Intent offerSummaryIntent = new Intent("android.intent.action.OFFERSUMMARY");
            offerSummaryIntent.putExtra("offerId", offerId);
            Recent.this.startActivity(offerSummaryIntent);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0185R.layout.recent);
        ((ImageView) findViewById(C0185R.id.backImageButton)).setOnClickListener(new C02001());
        populate();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    private void populate() {
        DBAdapter db = new DBAdapter(this);
        try {
            db.open();
            Cursor cOffers = db.getAllOffers();
            if (cOffers == null || cOffers.getCount() == 0) {
                ((TextView) findViewById(C0185R.id.empty)).setText("No offers found.");
                ((ListView) findViewById(C0185R.id.listView1)).setVisibility(8);
                findViewById(C0185R.id.empty).setVisibility(0);
                return;
            }
            this.offersItem = new ArrayList();
            cOffers.moveToFirst();
            for (int i = 0; i < cOffers.getCount(); i++) {
                String oIdAsString = new Integer(cOffers.getInt(0)).toString();
                String oDate = cOffers.getString(3);
                String pId = cOffers.getString(1);
                OfferObject oo = new OfferObject(oIdAsString, oDate, getProductName(pId), cOffers.getString(5), getContactName(cOffers.getString(2)), getProductPic(pId), cOffers.getInt(8), cOffers.getString(7));
                this.offersItem.add(oo);
                cOffers.moveToNext();
            }
            findViewById(C0185R.id.empty).setVisibility(8);
            ListView lv = (ListView) findViewById(C0185R.id.listView1);
            lv.setVisibility(0);
            lv.setOnItemClickListener(new C02012(lv));
            lv.setAdapter(new OffersAdapter(this, 0, this.offersItem));
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getProductName(java.lang.String r9) {
        /*
        r8 = this;
        r3 = "";
        r1 = new com.doviknissim.inegotiate.app.DBAdapter;
        r1.<init>(r8);
        r1.open();	 Catch:{ SQLException -> 0x0030 }
        r7 = java.lang.Long.valueOf(r9);	 Catch:{ SQLException -> 0x0030 }
        r5 = r7.longValue();	 Catch:{ SQLException -> 0x0030 }
        r0 = r1.getProduct(r5);	 Catch:{ SQLException -> 0x0030 }
        if (r0 == 0) goto L_0x001e;
    L_0x0018:
        r7 = r0.getCount();	 Catch:{ SQLException -> 0x0030 }
        if (r7 != 0) goto L_0x0023;
    L_0x001e:
        r1.close();
        r4 = r3;
    L_0x0022:
        return r4;
    L_0x0023:
        r0.moveToFirst();	 Catch:{ SQLException -> 0x0030 }
        r7 = 1;
        r3 = r0.getString(r7);	 Catch:{ SQLException -> 0x0030 }
        r1.close();
    L_0x002e:
        r4 = r3;
        goto L_0x0022;
    L_0x0030:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ all -> 0x0038 }
        r1.close();
        goto L_0x002e;
    L_0x0038:
        r7 = move-exception;
        r1.close();
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doviknissim.inegotiate.app.Recent.getProductName(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getProductPic(java.lang.String r9) {
        /*
        r8 = this;
        r3 = "";
        r1 = new com.doviknissim.inegotiate.app.DBAdapter;
        r1.<init>(r8);
        r1.open();	 Catch:{ SQLException -> 0x0030 }
        r7 = java.lang.Long.valueOf(r9);	 Catch:{ SQLException -> 0x0030 }
        r5 = r7.longValue();	 Catch:{ SQLException -> 0x0030 }
        r0 = r1.getProduct(r5);	 Catch:{ SQLException -> 0x0030 }
        if (r0 == 0) goto L_0x001e;
    L_0x0018:
        r7 = r0.getCount();	 Catch:{ SQLException -> 0x0030 }
        if (r7 != 0) goto L_0x0023;
    L_0x001e:
        r1.close();
        r4 = r3;
    L_0x0022:
        return r4;
    L_0x0023:
        r0.moveToFirst();	 Catch:{ SQLException -> 0x0030 }
        r7 = 3;
        r3 = r0.getString(r7);	 Catch:{ SQLException -> 0x0030 }
        r1.close();
    L_0x002e:
        r4 = r3;
        goto L_0x0022;
    L_0x0030:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ all -> 0x0038 }
        r1.close();
        goto L_0x002e;
    L_0x0038:
        r7 = move-exception;
        r1.close();
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doviknissim.inegotiate.app.Recent.getProductPic(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getContactName(java.lang.String r9) {
        /*
        r8 = this;
        r5 = "";
        r3 = new com.doviknissim.inegotiate.app.DBAdapter;
        r3.<init>(r8);
        r3.open();	 Catch:{ SQLException -> 0x0030 }
        r7 = java.lang.Long.valueOf(r9);	 Catch:{ SQLException -> 0x0030 }
        r1 = r7.longValue();	 Catch:{ SQLException -> 0x0030 }
        r0 = r3.getContact(r1);	 Catch:{ SQLException -> 0x0030 }
        if (r0 == 0) goto L_0x001e;
    L_0x0018:
        r7 = r0.getCount();	 Catch:{ SQLException -> 0x0030 }
        if (r7 != 0) goto L_0x0023;
    L_0x001e:
        r3.close();
        r6 = r5;
    L_0x0022:
        return r6;
    L_0x0023:
        r0.moveToFirst();	 Catch:{ SQLException -> 0x0030 }
        r7 = 1;
        r5 = r0.getString(r7);	 Catch:{ SQLException -> 0x0030 }
        r3.close();
    L_0x002e:
        r6 = r5;
        goto L_0x0022;
    L_0x0030:
        r4 = move-exception;
        r4.printStackTrace();	 Catch:{ all -> 0x0038 }
        r3.close();
        goto L_0x002e;
    L_0x0038:
        r7 = move-exception;
        r3.close();
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doviknissim.inegotiate.app.Recent.getContactName(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getProductdescription(java.lang.String r9) {
        /*
        r8 = this;
        r2 = "";
        r1 = new com.doviknissim.inegotiate.app.DBAdapter;
        r1.<init>(r8);
        r1.open();	 Catch:{ SQLException -> 0x0030 }
        r7 = java.lang.Long.valueOf(r9);	 Catch:{ SQLException -> 0x0030 }
        r5 = r7.longValue();	 Catch:{ SQLException -> 0x0030 }
        r0 = r1.getProduct(r5);	 Catch:{ SQLException -> 0x0030 }
        if (r0 == 0) goto L_0x001e;
    L_0x0018:
        r7 = r0.getCount();	 Catch:{ SQLException -> 0x0030 }
        if (r7 != 0) goto L_0x0023;
    L_0x001e:
        r1.close();
        r3 = r2;
    L_0x0022:
        return r3;
    L_0x0023:
        r0.moveToFirst();	 Catch:{ SQLException -> 0x0030 }
        r7 = 1;
        r2 = r0.getString(r7);	 Catch:{ SQLException -> 0x0030 }
        r1.close();
    L_0x002e:
        r3 = r2;
        goto L_0x0022;
    L_0x0030:
        r4 = move-exception;
        r4.printStackTrace();	 Catch:{ all -> 0x0038 }
        r1.close();
        goto L_0x002e;
    L_0x0038:
        r7 = move-exception;
        r1.close();
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doviknissim.inegotiate.app.Recent.getProductdescription(java.lang.String):java.lang.String");
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case CharacterEscapes.ESCAPE_NONE /*0*/:
                return new Builder(this).setTitle("Invalid input!").setMessage("Please enter a search string").setPositiveButton("OK", null).create();
            default:
                return null;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        View v = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);
        if (v instanceof EditText) {
            View w = getCurrentFocus();
            int[] scrcoords = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = (event.getRawX() + ((float) w.getLeft())) - ((float) scrcoords[0]);
            float y = (event.getRawY() + ((float) w.getTop())) - ((float) scrcoords[1]);
            Log.d("Activity", "Touch event " + event.getRawX() + "," + event.getRawY() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + x + "," + y + " rect " + w.getLeft() + "," + w.getTop() + "," + w.getRight() + "," + w.getBottom() + " coords " + scrcoords[0] + "," + scrcoords[1]);
            if (event.getAction() == 1 && (x < ((float) w.getLeft()) || x >= ((float) w.getRight()) || y < ((float) w.getTop()) || y > ((float) w.getBottom()))) {
                ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
            }
        }
        return ret;
    }
}
