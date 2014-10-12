package com.fatura.view.login;

import java.util.List;
import java.util.Objects;

import com.fatura.R;

import android.content.Context;
import android.widget.ArrayAdapter;

public class HintAdapter
extends ArrayAdapter<String> {

public HintAdapter(Context theContext, List<String> objects) {
super(theContext, R.id.login_form, R.id.sp_carrier, objects);
}

public HintAdapter(Context theContext, List<String> objects, int theLayoutResId) {
super(theContext, theLayoutResId, objects);
}

@Override
public int getCount() {
// don't display last item. It is used as hint.
int count = super.getCount();
return count > 0 ? count - 1 : count;
}
}