package cn.zdh.viewtounchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * viewGroup处理拦截事件
 */
public class ScrollView2 extends ScrollView {
    //手指滑动Y的距离
    private int downY;
    //获取触摸斜坡（作为滑动操作的参考值）
    private int mTouchSlop;

    public ScrollView2(Context context) {
        super(context);
        init(context);
    }

    public ScrollView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ScrollView2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    }

    /**
     * 外部处理拦截
     * 上下滑动冲突 就获取Y的触摸滑动距离
     * 如果左右滑动冲突就获取X的触摸滑动距离
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //获取滑动距离
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) ev.getRawY();
                //请求父布局不要拦截
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) ev.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    //请求父布局不要拦截
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}

