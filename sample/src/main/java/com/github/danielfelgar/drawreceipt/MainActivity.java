package com.github.danielfelgar.drawreceipt;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.github.danielfelgar.drawreceiptlib.ReceiptBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btDraw)
    Button btDraw;
    @BindView(R.id.ivReceipt)
    ImageView ivReceipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btDraw)
    public void drawReceipt(View view) {
        Bitmap barcode = BitmapFactory.decodeResource(this.getResources(), R.drawable.barcode);

        ReceiptBuilder receipt = new ReceiptBuilder(700);
        receipt.setMargin(30, 20).
                setAlign(Paint.Align.CENTER).
                setColor(Color.BLACK).
                setTextSize(30).
                setTypeface(this, "fonts/RobotoMono-Regular.ttf").
                addText("LakeFront Cafe").
                addText("1234 Main St.").
                addText("Palo Alto, CA 94568").
                addText("999-999-9999").
                addBlankSpace(60).
                setAlign(Paint.Align.LEFT).
                addText("Terminal ID: 123456", false).
                setAlign(Paint.Align.RIGHT).
                addText("1234").
                setAlign(Paint.Align.LEFT).
                addLine().
                addText("08/15/16", false).
                setAlign(Paint.Align.RIGHT).
                addText("SERVER #4").
                setAlign(Paint.Align.LEFT).
                addParagraph().
                addText("CHASE VISA - INSERT").
                addText("AID: A000000000011111").
                addText("ACCT #: *********1111").
                addParagraph().
                setTypeface(this, "fonts/RobotoMono-Bold.ttf").
                addText("CREDIT SALE").
                addText("UID: 12345678", false).
                setAlign(Paint.Align.RIGHT).
                addText("REF #: 1234").
                setTypeface(this, "fonts/RobotoMono-Regular.ttf").
                setAlign(Paint.Align.LEFT).
                addText("BATCH #: 091", false).
                setAlign(Paint.Align.RIGHT).
                addText("AUTH #: 0701C").
                setAlign(Paint.Align.LEFT).
                addParagraph().
                setTypeface(this, "fonts/RobotoMono-Bold.ttf").
                addText("AMOUNT", false).
                setAlign(Paint.Align.RIGHT).
                addText("$ 15.00").
                setAlign(Paint.Align.LEFT).
                addParagraph().
                addText("TIP", false).
                setAlign(Paint.Align.RIGHT).
                addText("$       ").
                addLine(120).
                setAlign(Paint.Align.LEFT).
                addParagraph().
                addText("TOTAL", false).
                setAlign(Paint.Align.RIGHT).
                addText("$       ").
                addLine(120).
                addParagraph().
                setAlign(Paint.Align.CENTER).
                setTypeface(this, "fonts/RobotoMono-Regular.ttf").
                addText("APPROVED").
                addParagraph().
                addImage(barcode);
        ivReceipt.setImageBitmap(receipt.build());
    }

}