package test.com.flickrgallery.IdlingResource;

import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Очень простая реализация {@link IdlingResource}.
 * <p>
 * Рассмотрите возможность использования CountingIdlingResource из пакета espresso-contrib, если вы используете этот класс из
 * несколько потоков или необходимо сохранить количество ожидающих операций.
 */

public class SimpleIdlingResource implements IdlingResource {

    @Nullable
    private volatile ResourceCallback mCallback;

    // Idleness контролируется с помощью этого булева.
    private AtomicBoolean mIsIdleNow = new AtomicBoolean(true);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return mIsIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallback = callback;
    }

    /**
     * Устанавливает новое состояние ожидания, если isIdleNow истинно, он пингует {@link ResourceCallback}.
     *
     * @param isIdleNow false, если есть ожидающие операции, true, если он неактивен.
     */
    public void setIdleState(boolean isIdleNow) {
        mIsIdleNow.set(isIdleNow);
        if (isIdleNow && mCallback != null) {
            mCallback.onTransitionToIdle();
        }
    }
}
