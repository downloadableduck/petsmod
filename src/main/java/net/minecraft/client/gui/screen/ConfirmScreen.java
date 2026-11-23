package net.minecraft.client.gui.screen;

/**
 * Modern confirmation screen used by Cloth Config, backed by BTA's screen system.
 *
 * <p>The first argument is the screen that receives {@link #confirmed(boolean, int)};
 * Cloth uses this as its callback rather than as the parent to return to.
 */
public class ConfirmScreen extends Screen {
    private final Screen callback;
    private final String title;
    private final String message;
    private final String yesText;
    private final String noText;
    private final int id;

    public ConfirmScreen(Screen callback, String title, String message, String yesText, String noText, int id) {
        super(callback);
        this.callback = callback;
        this.title = title;
        this.message = message;
        this.yesText = yesText;
        this.noText = noText;
        this.id = id;
    }

    public Screen getCallback() {
        return this.callback;
    }

    public String getTitleText() {
        return this.title;
    }

    public String getMessage() {
        return this.message;
    }

    public String getYesText() {
        return this.yesText;
    }

    public String getNoText() {
        return this.noText;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public void confirmed(boolean result, int buttonId) {
        if (this.callback != null) {
            this.callback.confirmed(result, buttonId);
        }
    }
}
