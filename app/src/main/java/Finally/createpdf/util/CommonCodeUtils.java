package Finally.createpdf.util;

import android.app.Activity;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Finally.createpdf.R;
import Finally.createpdf.adapter.ExtractImagesAdapter;
import Finally.createpdf.adapter.MergeFilesAdapter;
import Finally.createpdf.model.HomePageItem;


public class CommonCodeUtils {

    /**
     * updates the output recycler view if paths.size > 0
     * else give the main view
     */
    public void populateUtil(Activity mActivity, ArrayList<String> paths,
                                    MergeFilesAdapter.OnClickListener onClickListener,
                                    RelativeLayout layout, LottieAnimationView animationView,
                                    RecyclerView recyclerView) {

        if (paths == null || paths.size() == 0) {
            layout.setVisibility(View.GONE);
        } else {
            // Init recycler view
            recyclerView.setVisibility(View.VISIBLE);
            MergeFilesAdapter mergeFilesAdapter = new MergeFilesAdapter(mActivity,
                    paths, false, onClickListener);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(mergeFilesAdapter);
            recyclerView.addItemDecoration(new ViewFilesDividerItemDecoration(mActivity));
        }
        animationView.setVisibility(View.GONE);
    }


    /**
     * sets the appropriate text to success Text View & display images in adapter
     */
    public void updateView(Activity mActivity, int imageCount, ArrayList<String> outputFilePaths,
                                  TextView successTextView, LinearLayout options, RecyclerView mCreatedImages,
                                  ExtractImagesAdapter.OnFileItemClickedListener listener) {

        if (imageCount == 0) {
            StringUtils.getInstance().showSnackbar(mActivity, R.string.extract_images_failed);
            return;
        }

        String text = String.format(mActivity.getString(R.string.extract_images_success), imageCount);
        StringUtils.getInstance().showSnackbar(mActivity, text);
        successTextView.setVisibility(View.VISIBLE);
        options.setVisibility(View.VISIBLE);
        ExtractImagesAdapter extractImagesAdapter = new ExtractImagesAdapter(mActivity, outputFilePaths, listener);
        // init recycler view for displaying generated image list
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
        successTextView.setText(text);
        mCreatedImages.setVisibility(View.VISIBLE);
        mCreatedImages.setLayoutManager(mLayoutManager);
        // set up adapter
        mCreatedImages.setAdapter(extractImagesAdapter);
        mCreatedImages.addItemDecoration(new ViewFilesDividerItemDecoration(mActivity));
    }
    /**
     * Closes the bottom sheet if it is expanded
     */

    public void closeBottomSheetUtil(BottomSheetBehavior sheetBehavior) {
        if (checkSheetBehaviourUtil(sheetBehavior))
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
    /**
     * Checks whether the bottom sheet is expanded or collapsed
     */
    public boolean checkSheetBehaviourUtil(BottomSheetBehavior sheetBehavior) {
        return sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED;
    }

    public Map<Integer, HomePageItem> fillNavigationItemsMap(boolean homePageItems) {

        // if homepage items is false, it's favourite items
        Map<Integer, HomePageItem> mFragmentPositionMap;

        mFragmentPositionMap = new HashMap<>();
        mFragmentPositionMap.put(homePageItems ? R.id.images_to_pdf : R.id.images_to_pdf_fav,
                new HomePageItem(R.id.nav_camera,
                        R.drawable.ic_menu_camera,  R.string.images_to_pdf));

        mFragmentPositionMap
                .put(homePageItems ? R.id.qr_barcode_to_pdf : R.id.qr_barcode_to_pdf_fav,
                new HomePageItem(R.id.nav_qrcode,
                        R.drawable.ic_qrcode_24dp, R.string.qr_barcode_pdf));

        mFragmentPositionMap.put(homePageItems ? R.id.excel_to_pdf : R.id.excel_to_pdf_fav,
                new HomePageItem(R.id.nav_excel_to_pdf,
                        R.drawable.ic_excel, R.string.excel_to_pdf));

        mFragmentPositionMap.put(homePageItems ? R.id.view_files : R.id.view_files_fav,
                new HomePageItem(R.id.nav_gallery,
                        R.drawable.ic_menu_gallery, R.string.viewFiles));

        mFragmentPositionMap.put(homePageItems ? R.id.rotate_pages : R.id.rotate_pages_fav,
                new HomePageItem(R.id.nav_gallery,
                        R.drawable.baseline_crop_rotate_24, R.string.rotate_pages));

        mFragmentPositionMap.put(homePageItems ? R.id.extract_text : R.id.extract_text_fav,
                new HomePageItem(R.id.nav_text_extract,
                        R.drawable.ic_broken_image_black_24dp, R.string.extract_text));

        mFragmentPositionMap.put(homePageItems ? R.id.add_watermark : R.id.add_watermark_fav,
                new HomePageItem(R.id.nav_add_watermark,
                        R.drawable.ic_branding_watermark_black_24dp, R.string.add_watermark));

        mFragmentPositionMap.put(homePageItems ? R.id.merge_pdf : R.id.merge_pdf_fav,
                new HomePageItem(R.id.nav_merge,
                        R.drawable.ic_merge_type_black_24dp, R.string.merge_pdf));

        mFragmentPositionMap.put(homePageItems ? R.id.split_pdf : R.id.split_pdf_fav,
                new HomePageItem(R.id.nav_split,
                        R.drawable.ic_call_split_black_24dp, R.string.split_pdf));

        mFragmentPositionMap.put(homePageItems ? R.id.text_to_pdf : R.id.text_to_pdf_fav,
                new HomePageItem(R.id.nav_text_to_pdf,
                        R.drawable.ic_text_format_black_24dp, R.string.text_to_pdf));

        mFragmentPositionMap.put(homePageItems ? R.id.compress_pdf : R.id.compress_pdf_fav,
                new HomePageItem(R.id.nav_compress_pdf,
                        R.drawable.ic_compress_image, R.string.compress_pdf));

        mFragmentPositionMap.put(homePageItems ? R.id.remove_pages : R.id.remove_pages_fav,
                new HomePageItem(R.id.nav_remove_pages,
                        R.drawable.ic_remove_circle_black_24dp, R.string.remove_pages));

        mFragmentPositionMap.put(homePageItems ? R.id.rearrange_pages : R.id.rearrange_pages_fav,
                new HomePageItem(R.id.nav_rearrange_pages,
                        R.drawable.ic_rearrange, R.string.reorder_pages));

        mFragmentPositionMap.put(homePageItems ? R.id.extract_images : R.id.extract_images_fav,
                new HomePageItem(R.id.nav_extract_images,
                        R.drawable.ic_broken_image_black_24dp, R.string.extract_images));

        mFragmentPositionMap.put(homePageItems ? R.id.view_history : R.id.view_history_fav,
                new HomePageItem(R.id.nav_history,
                        R.drawable.ic_history_black_24dp, R.string.history));

        mFragmentPositionMap.put(homePageItems ? R.id.pdf_to_images : R.id.pdf_to_images_fav,
                new HomePageItem(R.id.nav_pdf_to_images,
                        R.drawable.ic_image_black_24dp, R.string.pdf_to_images));

        mFragmentPositionMap.put(homePageItems ? R.id.add_password : R.id.add_password_fav,
                new HomePageItem(R.id.nav_add_password,
                        R.drawable.ic_lock_black_24dp, R.string.add_password));

        mFragmentPositionMap.put(homePageItems ? R.id.remove_password : R.id.remove_password_fav,
                new HomePageItem(R.id.nav_remove_password,
                        R.drawable.ic_lock_open_black_24dp, R.string.remove_password));

        mFragmentPositionMap.put(homePageItems ? R.id.add_images : R.id.add_images_fav,
                new HomePageItem(R.id.nav_add_images,
                        R.drawable.ic_add_black_24dp, R.string.add_images));

        mFragmentPositionMap.put(homePageItems ?
                        R.id.remove_duplicates_pages_pdf : R.id.remove_duplicates_pages_pdf_fav,
                new HomePageItem(R.id.nav_remove_duplicate_pages,
                        R.drawable.ic_remove_duplicate_square_black, R.string.remove_duplicate_pages));

        mFragmentPositionMap.put(homePageItems ? R.id.invert_pdf : R.id.invert_pdf_fav,
                new HomePageItem(R.id.nav_invert_pdf,
                        R.drawable.ic_invert_color_24dp, R.string.invert_pdf));

        mFragmentPositionMap.put(homePageItems ? R.id.zip_to_pdf : R.id.zip_to_pdf_fav,
                new HomePageItem(R.id.nav_zip_to_pdf,
                        R.drawable.ic_zip_to_pdf, R.string.zip_to_pdf));

        mFragmentPositionMap.put(homePageItems ? R.id.add_text : R.id.add_text_fav,
                new HomePageItem(R.id.nav_add_text,
                        R.drawable.ic_text_format_black_24dp, R.string.add_text));

        return mFragmentPositionMap;

    }

    private static class SingletonHolder {
        static final CommonCodeUtils INSTANCE = new CommonCodeUtils();
    }

    public static CommonCodeUtils getInstance() {
        return CommonCodeUtils.SingletonHolder.INSTANCE;
    }
}
