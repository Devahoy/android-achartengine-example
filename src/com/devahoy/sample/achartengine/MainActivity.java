package com.devahoy.sample.achartengine;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MainActivity extends ActionBarActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {}
        
        private View mView;
        private GraphicalView mGraphView;
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            mView = rootView;
            initData();
            return rootView;
        }
        
        private void initData() {
            String[] months = {
                    "JAN", "FEB", "MAR", "APR", "MAY", "JUN",
                    "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"
            };

            int[] index = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

            int[] incomeA = {4000, 5500, 2300, 2100, 2500, 2900, 3200, 2400, 1800, 2100, 3500, 5900};
            int[] incomeB = {3600, 4500, 3200, 3600, 2800, 1800, 2100, 2900, 2200, 2500, 4000, 3500};
            int[] incomeC = {4300, 4000, 3000, 3200, 2400, 2500, 2600, 3400, 3900, 4500, 5000, 4500};
            
            XYSeries seriesA = new XYSeries("Googla");
            XYSeries seriesB = new XYSeries("Microsa");
            XYSeries seriesC = new XYSeries("Appla");
  
            int length = index.length;
            for (int i = 0; i < length; i++) {
                seriesA.add(index[i], incomeA[i]);
                seriesB.add(index[i], incomeB[i]);
                seriesC.add(index[i], incomeC[i]);
            }
            
            XYSeriesRenderer rendererA = new XYSeriesRenderer();
            rendererA.setPointStyle(PointStyle.CIRCLE);
            rendererA.setColor(Color.RED);
            rendererA.setLineWidth(2);

            XYSeriesRenderer rendererB = new XYSeriesRenderer();
            rendererB.setPointStyle(PointStyle.X);
            rendererB.setColor(Color.BLUE);
            rendererB.setLineWidth(2);

            XYSeriesRenderer rendererC = new XYSeriesRenderer();
            rendererC.setPointStyle(PointStyle.DIAMOND);
            rendererC.setColor(Color.GREEN);
            rendererC.setLineWidth(2);
            
            XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
            dataset.addSeries(seriesA);
            dataset.addSeries(seriesB);
            dataset.addSeries(seriesC);
            


            XYMultipleSeriesRenderer multipleSeriesRenderer
                    = new XYMultipleSeriesRenderer();

            for (int i = 0; i < length; i++) {
                multipleSeriesRenderer.addXTextLabel(i + 1, months[i]);
            }
            multipleSeriesRenderer.setChartTitle("ตัวอย่างกราฟเส้น (Line Chart)");
            multipleSeriesRenderer.setYTitle("ยอดขายรวม(สตางค์)");
            multipleSeriesRenderer.setXTitle("ปี พ.ศ. 2600");
            multipleSeriesRenderer.setZoomButtonsVisible(true);
            
            multipleSeriesRenderer.setXLabels(0);
            multipleSeriesRenderer.setBackgroundColor(Color.WHITE);
            multipleSeriesRenderer.setApplyBackgroundColor(true);
            multipleSeriesRenderer.setMarginsColor(Color.WHITE);
            multipleSeriesRenderer.setLabelsColor(Color.BLACK);
            multipleSeriesRenderer.setAxesColor(Color.GRAY);
            multipleSeriesRenderer.setYLabelsColor(0, Color.BLACK);
            multipleSeriesRenderer.setXLabelsColor(Color.BLACK);

            multipleSeriesRenderer.addSeriesRenderer(rendererA);
            multipleSeriesRenderer.addSeriesRenderer(rendererB);
            multipleSeriesRenderer.addSeriesRenderer(rendererC);
            

            drawChart(dataset, multipleSeriesRenderer);
        }

        private void drawChart(XYMultipleSeriesDataset dataset,
                               XYMultipleSeriesRenderer renderer) {
        	
        	if (null == mGraphView) {
        		mGraphView =
                        ChartFactory.getLineChartView(getActivity(), dataset, renderer);

                RelativeLayout container =
                        (RelativeLayout) mView.findViewById(R.id.graph_container);

                container.addView(mGraphView);
        	} else {
        		mGraphView.repaint();
        	}
        }
        
    }
 
}
