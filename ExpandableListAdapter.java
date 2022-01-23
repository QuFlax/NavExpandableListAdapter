import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private static final int[][] GROUP_STATES = {{}, {android.R.attr.state_expanded}};
    private final LayoutInflater mInflater;
    private String[] GNames;
    private String[][] CNames;
    private int[] GIcons;
    private int[][] CIcons;

    public ExpandableListAdapter(AppCompatActivity activity, String[] _GNames, String[][] _CNames) {
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        GNames = _GNames; CNames = _CNames; GIcons = null; CIcons = null;
    }
    public ExpandableListAdapter(AppCompatActivity activity, String[] _GNames, String[][] _CNames,
                                 int[] _GIcons, int[][] _CIcons) {
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        GNames = _GNames; CNames = _CNames; GIcons = _GIcons; CIcons = _CIcons;
    }

    public int getGroupCount() { return GNames.length; }
    public int getChildrenCount(int g) { return CNames[g].length; }
    public Object getGroup(int g) { return GNames[g]; }
    public Object getChild(int g, int c) { return CNames[g][c]; }
    public long getGroupId(int g) { return g; }
    public long getChildId(int g, int c) { return c; }
    public boolean hasStableIds() { return true; }
    public boolean isChildSelectable(int g, int c) { return true; }
    public View getGroupView (int g, boolean isExpanded, View view, ViewGroup parent) {
        return getView(true, g, 0, isExpanded, view, parent); }
    public View getChildView(int g, int c, boolean isLastChild, View view, ViewGroup parent) {
        return getView(false, g, c, isLastChild, view, parent); }

    public boolean hasChildren(int g) { return getChildrenCount(g) != 0; }
    private View getView(boolean isG, int g, int c, boolean isE, View view, ViewGroup parent) {
        View v = (view != null) ? view : mInflater.inflate(
                isG ? R.layout.nav_list_group : R.layout.nav_list_child, parent, false);
        String name = isG ? GNames[g] : CNames[g][c];
        int icon = isG ? GIcons[g] : CIcons[g][c];
        if (isG) {
            ImageView indicator = v.findViewById(R.id.explist_indicator);
            indicator.setVisibility(hasChildren(g) ? View.VISIBLE : View.INVISIBLE);
            if (hasChildren(g)) indicator.getDrawable().setState(GROUP_STATES[isE?1:0]);
        }
        else if (name == null || name.isEmpty()) return v;
        ((ImageView) v.findViewById(R.id.icon)).setImageResource(icon);
        ((TextView) v.findViewById(R.id.name)).setText(name); return v;
    }
}
