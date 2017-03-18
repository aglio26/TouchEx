package com.example.madlax.touchex;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;
import android.content.Context;
import java.util.HashMap;
import android.view.MotionEvent;
import android.graphics.PointF;
import android.graphics.Paint;

/**
 * Created by madlax on 2017/02/17.
 */
//目標：マップの描画
//画面サイズの取得

//原点の取得

//スマホ上グリッドの取得

//マップ定数と画像座標の紐付け

//描画処理





public class TouchView extends View {
    private HashMap<String, PointF> points = new HashMap<String, PointF>();

    //コンストラクタ
    public TouchView(Context context) {
        super(context);
        setBackgroundColor(Color.WHITE);
    }

    //描画時に呼ばれる
    @Override
    protected void onDraw(Canvas canvas) {
        //描画オブジェクト生成
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(48);
        //タッチXY座標の描画
        canvas.drawText("TouchEx>", 0, 60 * 1, paint);
        Object[] keys = points.keySet().toArray();
        for (int i = 0; i < keys.length; i++) {
            PointF pos = (PointF) points.get(keys[i]);
            canvas.drawText((int) pos.x + "," + (int) pos.y, 0, 120 + 60 * i, paint);
        }
    }

    //タッチ時処理
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //アクション種別とタッチ数の取得
        int action = event.getAction();
        int count = event.getPointerCount();

        //アクションインデックスとポインタIDの取得
        int index = event.getActionIndex();
        int pointerID = event.getPointerId(index);

        //タッチ位置の取得
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                points.put("" + pointerID, new PointF(event.getX(), event.getY()));
                break;
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < count; i++) {
                    PointF pos = points.get("" + event.getPointerId(i));
                    pos.x = event.getX(9);
                    pos.y = event.getY(i);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                points.remove("" + pointerID);
                break;
        }
        invalidate();
        return true;
    }
}
