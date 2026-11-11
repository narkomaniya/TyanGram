package org.telegram.messenger;

import android.text.TextUtils;

import androidx.annotation.IntDef;

import org.telegram.tgnet.ConnectionsManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Stubbed push controller: Firebase/GCM removed for privacy (TyanGram).
 * Keeps the public API surface used by the rest of the app.
 */
public class PushListenerController {
    public static final int PUSH_TYPE_FIREBASE = 2;
    public static final int PUSH_TYPE_HUAWEI = 13;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({PUSH_TYPE_FIREBASE, PUSH_TYPE_HUAWEI})
    public @interface PushType {}

    public interface IPushListenerServiceProvider {
        boolean hasServices();
        String getLogTitle();
        @PushType int getPushType();
        void onRequestPushToken();
    }

    public static class GooglePushListenerServiceProvider implements IPushListenerServiceProvider {
        public static final GooglePushListenerServiceProvider INSTANCE = new GooglePushListenerServiceProvider();

        @Override
        public boolean hasServices() {
            return false;
        }

        @Override
        public String getLogTitle() {
            return "Google Play Services";
        }

        @Override
        public @PushType int getPushType() {
            return PUSH_TYPE_FIREBASE;
        }

        @Override
        public void onRequestPushToken() {
            // no-op: FCM removed
        }
    }

    public static void sendRegistrationToServer(@PushType int pushType, String token) {
        Utilities.stageQueue.postRunnable(() -> {
            ConnectionsManager.setRegId(token, pushType, SharedConfig.pushStringStatus);
            if (token == null) {
                return;
            }
            SharedConfig.pushString = token;
            SharedConfig.pushType = pushType;
            for (int a = 0; a < UserConfig.MAX_ACCOUNT_COUNT; a++) {
                UserConfig userConfig = UserConfig.getInstance(a);
                userConfig.registeredForPush = false;
                userConfig.saveConfig(false);
                if (userConfig.getClientUserId() != 0) {
                    final int currentAccount = a;
                    AndroidUtilities.runOnUIThread(() -> MessagesController.getInstance(currentAccount).registerForPush(pushType, token));
                }
            }
        });
    }

    // Kept for binary/source compatibility; GCM service removed so this should not be called.
    public static void processRemoteMessage(@PushType int pushType, String data, long time) {
        // no-op
    }
}
