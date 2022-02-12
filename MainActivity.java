protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        ExpandableListAdapter adapter = new ExpandableListAdapter(this, ExpandableListConst.GROUP_NAMES, ExpandableListConst.CHILD_NAMES, ExpandableListConst.GROUP_ICONS, ExpandableListConst.CHILD_ICONS);
        binding.navigationmenu.setAdapter(adapter);
        binding.navigationmenu.setOnChildClickListener((e, view, g, c, id) -> closeDrawers(true));
        binding.navigationmenu.setOnGroupClickListener((e, view, g, id) -> closeDrawers(!adapter.hasChildren(g)));
}
public Fragment getCurrentFragment() { return getSupportFragmentManager().findFragmentById(R.id.fragment_container); }
public boolean closeDrawers(boolean b) { if (b) binding.drawerLayout.closeDrawers(); return b; }
public void openUrl(String s) { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(s))); }
