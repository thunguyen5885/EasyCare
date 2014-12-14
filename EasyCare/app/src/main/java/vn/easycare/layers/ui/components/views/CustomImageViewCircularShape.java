package vn.easycare.layers.ui.components.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by ThuNguyen on 12/14/2014.
 */
public class CustomImageViewCircularShape extends NetworkImageView{
    //you can change the radius to modify the circlur shape into oval or rounded rectangle

    public static float radius = 35.0f;

    public CustomImageViewCircularShape(Context context) {
        super(context);
    }

    public CustomImageViewCircularShape(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageViewCircularShape(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//float radius = 36.0f;
        Path clipPath = new Path();
        RectF rect = new RectF(0, 0, this.getWidth(), this.getHeight());
        clipPath.addRoundRect(rect, radius, radius, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }
}
