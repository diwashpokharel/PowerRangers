package ca.brocku.ma14ht.profile;

import android.widget.EditText;

/**
 * Created by Maysara Al Jumaily on 2018-02-22.
 */

public class EditTextAtt {
    private EditText component;
    private int id;
    private int minSize;
    private int maxSize;
    private int inputType;

    public EditTextAtt(EditText component, int id, int minSize, int maxSize, int inputType) {
        this.component = component;
        this.id = id;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.inputType = inputType;
    }

    public EditText getComponent() {
        return component;
    }

    public int getId() {
        return id;
    }

    public int getMinSize() {
        return minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getInputType() {
        return inputType;
    }

    private void setComponent(EditText component){
        this.component = component;
    }

    private void setId(int id){
        this.id = id;
    }
}
