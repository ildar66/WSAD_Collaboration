/*
 * Created on Apr 21, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pagecode;

import javax.faces.component.html.HtmlForm;
import com.ibm.faces.bf.component.html.HtmlClientData;
import com.ibm.faces.bf.component.html.HtmlTabbedPanel;
import com.ibm.faces.bf.component.html.HtmlBfPanel;
import com.ibm.faces.bf.component.html.HtmlTree;
import com.ibm.faces.bf.component.html.HtmlTreeNodeAttr;
import com.ibm.faces.bf.component.html.HtmlDataGrid;
import com.ibm.faces.bf.component.html.HtmlDataGridCol;
import javax.faces.component.html.HtmlOutputText;
import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.bf.component.html.HtmlWebService;
import com.ibm.faces.bf.component.html.HtmlWsInput;
import com.ibm.faces.bf.component.html.HtmlWsOutput;
import com.ibm.faces.bf.component.html.HtmlGraphDraw;
import com.ibm.faces.bf.component.html.HtmlGraphDrawData;
import com.ibm.faces.bf.component.html.HtmlGraphDrawLabels;
import javax.faces.component.html.HtmlInputText;
import com.ibm.odcb.tutorial.businessobjects.Root;
/**
 * @author fenil
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PortfolioJSF extends PageCodeBase {

	protected HtmlForm PortfolioSubmit;
	protected HtmlClientData RootDataModel;
	protected HtmlTabbedPanel upperTabbedPanel;
	protected HtmlBfPanel treePanel;
	protected HtmlTree tree1;
	protected HtmlTreeNodeAttr Root2;
	protected HtmlTreeNodeAttr Users;
	protected HtmlTreeNodeAttr Portfolio;
	protected HtmlBfPanel datagridPanel;
	protected HtmlDataGrid dataGrid1;
	protected HtmlDataGridCol grid1col1;
	protected HtmlDataGridCol grid1col2;
	protected HtmlDataGridCol grid1col3;
	protected HtmlDataGridCol grid1col4;
	protected HtmlDataGridCol grid1col5;
	protected HtmlDataGridCol grid1col6;
	protected HtmlDataGridCol grid1col7;
	protected HtmlBfPanel fullPanel;
	protected HtmlTree tree2;
	protected HtmlTreeNodeAttr Root22;
	protected HtmlTreeNodeAttr Users2;
	protected HtmlTreeNodeAttr Portfolio2;
	protected HtmlOutputText phUserName;
	protected HtmlOutputText phPortName;
	protected HtmlDataGrid dataGrid2;
	protected HtmlDataGridCol grid2col1;
	protected HtmlDataGridCol grid2col2;
	protected HtmlDataGridCol grid2col3;
	protected HtmlDataGridCol grid2col4;
	protected HtmlDataGridCol grid2col5;
	protected HtmlDataGridCol grid2col6;
	protected HtmlDataGridCol grid2col7;
	protected HtmlDataGridCol grid2col8;
	protected HtmlCommandExButton upperTabbedPanel_back;
	protected HtmlCommandExButton upperTabbedPanel_next;
	protected HtmlCommandExButton upperTabbedPanel_finish;
	protected HtmlCommandExButton upperTabbedPanel_cancel;
	protected HtmlWebService webservice1;
	protected HtmlWsInput wbinput1;
	protected HtmlWsOutput wboutput1;
	protected HtmlWsOutput wbinput2;
	protected HtmlWsOutput wboutput2;
	protected HtmlTabbedPanel lowerTabbedPanel;
	protected HtmlBfPanel graphPanel;
	protected HtmlGraphDraw graphDraw1;
	protected HtmlGraphDrawData graphDrawData1;
	protected HtmlGraphDrawLabels graphDrawLabels1;
	protected HtmlBfPanel webServicesPanel;
	protected HtmlInputText sb1;
	protected HtmlOutputText sym1;
	protected HtmlOutputText pr1;
	protected HtmlOutputText chg1;
	protected HtmlOutputText pc1;
	protected HtmlOutputText vol1;
	protected HtmlCommandExButton lowerTabbedPanel_back;
	protected HtmlCommandExButton lowerTabbedPanel_next;
	protected HtmlCommandExButton lowerTabbedPanel_finish;
	protected HtmlCommandExButton lowerTabbedPanel_cancel;
	protected Root root;
	protected HtmlForm getPortfolioSubmit() {
		if (PortfolioSubmit == null) {
			PortfolioSubmit = (HtmlForm) findComponentInRoot("PortfolioSubmit");
		}
		return PortfolioSubmit;
	}
	protected HtmlClientData getRootDataModel() {
		if (RootDataModel == null) {
			RootDataModel =
				(HtmlClientData) findComponentInRoot("RootDataModel");
		}
		return RootDataModel;
	}
	protected HtmlTabbedPanel getUpperTabbedPanel() {
		if (upperTabbedPanel == null) {
			upperTabbedPanel =
				(HtmlTabbedPanel) findComponentInRoot("upperTabbedPanel");
		}
		return upperTabbedPanel;
	}
	protected HtmlBfPanel getTreePanel() {
		if (treePanel == null) {
			treePanel = (HtmlBfPanel) findComponentInRoot("treePanel");
		}
		return treePanel;
	}
	protected HtmlTree getTree1() {
		if (tree1 == null) {
			tree1 = (HtmlTree) findComponentInRoot("tree1");
		}
		return tree1;
	}
	protected HtmlTreeNodeAttr getRoot2() {
		if (Root2 == null) {
			Root2 = (HtmlTreeNodeAttr) findComponentInRoot("Root2");
		}
		return Root2;
	}
	protected HtmlTreeNodeAttr getUsers() {
		if (Users == null) {
			Users = (HtmlTreeNodeAttr) findComponentInRoot("Users");
		}
		return Users;
	}
	protected HtmlTreeNodeAttr getPortfolio() {
		if (Portfolio == null) {
			Portfolio = (HtmlTreeNodeAttr) findComponentInRoot("Portfolio");
		}
		return Portfolio;
	}
	protected HtmlBfPanel getDatagridPanel() {
		if (datagridPanel == null) {
			datagridPanel = (HtmlBfPanel) findComponentInRoot("datagridPanel");
		}
		return datagridPanel;
	}
	protected HtmlDataGrid getDataGrid1() {
		if (dataGrid1 == null) {
			dataGrid1 = (HtmlDataGrid) findComponentInRoot("dataGrid1");
		}
		return dataGrid1;
	}
	protected HtmlDataGridCol getGrid1col1() {
		if (grid1col1 == null) {
			grid1col1 = (HtmlDataGridCol) findComponentInRoot("grid1col1");
		}
		return grid1col1;
	}
	protected HtmlDataGridCol getGrid1col2() {
		if (grid1col2 == null) {
			grid1col2 = (HtmlDataGridCol) findComponentInRoot("grid1col2");
		}
		return grid1col2;
	}
	protected HtmlDataGridCol getGrid1col3() {
		if (grid1col3 == null) {
			grid1col3 = (HtmlDataGridCol) findComponentInRoot("grid1col3");
		}
		return grid1col3;
	}
	protected HtmlDataGridCol getGrid1col4() {
		if (grid1col4 == null) {
			grid1col4 = (HtmlDataGridCol) findComponentInRoot("grid1col4");
		}
		return grid1col4;
	}
	protected HtmlDataGridCol getGrid1col5() {
		if (grid1col5 == null) {
			grid1col5 = (HtmlDataGridCol) findComponentInRoot("grid1col5");
		}
		return grid1col5;
	}
	protected HtmlDataGridCol getGrid1col6() {
		if (grid1col6 == null) {
			grid1col6 = (HtmlDataGridCol) findComponentInRoot("grid1col6");
		}
		return grid1col6;
	}
	protected HtmlDataGridCol getGrid1col7() {
		if (grid1col7 == null) {
			grid1col7 = (HtmlDataGridCol) findComponentInRoot("grid1col7");
		}
		return grid1col7;
	}
	protected HtmlBfPanel getFullPanel() {
		if (fullPanel == null) {
			fullPanel = (HtmlBfPanel) findComponentInRoot("fullPanel");
		}
		return fullPanel;
	}
	protected HtmlTree getTree2() {
		if (tree2 == null) {
			tree2 = (HtmlTree) findComponentInRoot("tree2");
		}
		return tree2;
	}
	protected HtmlTreeNodeAttr getRoot22() {
		if (Root22 == null) {
			Root22 = (HtmlTreeNodeAttr) findComponentInRoot("Root22");
		}
		return Root22;
	}
	protected HtmlTreeNodeAttr getUsers2() {
		if (Users2 == null) {
			Users2 = (HtmlTreeNodeAttr) findComponentInRoot("Users2");
		}
		return Users2;
	}
	protected HtmlTreeNodeAttr getPortfolio2() {
		if (Portfolio2 == null) {
			Portfolio2 = (HtmlTreeNodeAttr) findComponentInRoot("Portfolio2");
		}
		return Portfolio2;
	}
	protected HtmlOutputText getPhUserName() {
		if (phUserName == null) {
			phUserName = (HtmlOutputText) findComponentInRoot("phUserName");
		}
		return phUserName;
	}
	protected HtmlOutputText getPhPortName() {
		if (phPortName == null) {
			phPortName = (HtmlOutputText) findComponentInRoot("phPortName");
		}
		return phPortName;
	}
	protected HtmlDataGrid getDataGrid2() {
		if (dataGrid2 == null) {
			dataGrid2 = (HtmlDataGrid) findComponentInRoot("dataGrid2");
		}
		return dataGrid2;
	}
	protected HtmlDataGridCol getGrid2col1() {
		if (grid2col1 == null) {
			grid2col1 = (HtmlDataGridCol) findComponentInRoot("grid2col1");
		}
		return grid2col1;
	}
	protected HtmlDataGridCol getGrid2col2() {
		if (grid2col2 == null) {
			grid2col2 = (HtmlDataGridCol) findComponentInRoot("grid2col2");
		}
		return grid2col2;
	}
	protected HtmlDataGridCol getGrid2col3() {
		if (grid2col3 == null) {
			grid2col3 = (HtmlDataGridCol) findComponentInRoot("grid2col3");
		}
		return grid2col3;
	}
	protected HtmlDataGridCol getGrid2col4() {
		if (grid2col4 == null) {
			grid2col4 = (HtmlDataGridCol) findComponentInRoot("grid2col4");
		}
		return grid2col4;
	}
	protected HtmlDataGridCol getGrid2col5() {
		if (grid2col5 == null) {
			grid2col5 = (HtmlDataGridCol) findComponentInRoot("grid2col5");
		}
		return grid2col5;
	}
	protected HtmlDataGridCol getGrid2col6() {
		if (grid2col6 == null) {
			grid2col6 = (HtmlDataGridCol) findComponentInRoot("grid2col6");
		}
		return grid2col6;
	}
	protected HtmlDataGridCol getGrid2col7() {
		if (grid2col7 == null) {
			grid2col7 = (HtmlDataGridCol) findComponentInRoot("grid2col7");
		}
		return grid2col7;
	}
	protected HtmlDataGridCol getGrid2col8() {
		if (grid2col8 == null) {
			grid2col8 = (HtmlDataGridCol) findComponentInRoot("grid2col8");
		}
		return grid2col8;
	}
	protected HtmlCommandExButton getUpperTabbedPanel_back() {
		if (upperTabbedPanel_back == null) {
			upperTabbedPanel_back =
				(HtmlCommandExButton) findComponentInRoot("upperTabbedPanel_back");
		}
		return upperTabbedPanel_back;
	}
	protected HtmlCommandExButton getUpperTabbedPanel_next() {
		if (upperTabbedPanel_next == null) {
			upperTabbedPanel_next =
				(HtmlCommandExButton) findComponentInRoot("upperTabbedPanel_next");
		}
		return upperTabbedPanel_next;
	}
	protected HtmlCommandExButton getUpperTabbedPanel_finish() {
		if (upperTabbedPanel_finish == null) {
			upperTabbedPanel_finish =
				(HtmlCommandExButton) findComponentInRoot("upperTabbedPanel_finish");
		}
		return upperTabbedPanel_finish;
	}
	protected HtmlCommandExButton getUpperTabbedPanel_cancel() {
		if (upperTabbedPanel_cancel == null) {
			upperTabbedPanel_cancel =
				(HtmlCommandExButton) findComponentInRoot("upperTabbedPanel_cancel");
		}
		return upperTabbedPanel_cancel;
	}
	protected HtmlWebService getWebservice1() {
		if (webservice1 == null) {
			webservice1 = (HtmlWebService) findComponentInRoot("webservice1");
		}
		return webservice1;
	}
	protected HtmlWsInput getWbinput1() {
		if (wbinput1 == null) {
			wbinput1 = (HtmlWsInput) findComponentInRoot("wbinput1");
		}
		return wbinput1;
	}
	protected HtmlWsOutput getWboutput1() {
		if (wboutput1 == null) {
			wboutput1 = (HtmlWsOutput) findComponentInRoot("wboutput1");
		}
		return wboutput1;
	}
	protected HtmlWsOutput getWbinput2() {
		if (wbinput2 == null) {
			wbinput2 = (HtmlWsOutput) findComponentInRoot("wbinput2");
		}
		return wbinput2;
	}
	protected HtmlWsOutput getWboutput2() {
		if (wboutput2 == null) {
			wboutput2 = (HtmlWsOutput) findComponentInRoot("wboutput2");
		}
		return wboutput2;
	}
	protected HtmlTabbedPanel getLowerTabbedPanel() {
		if (lowerTabbedPanel == null) {
			lowerTabbedPanel =
				(HtmlTabbedPanel) findComponentInRoot("lowerTabbedPanel");
		}
		return lowerTabbedPanel;
	}
	protected HtmlBfPanel getGraphPanel() {
		if (graphPanel == null) {
			graphPanel = (HtmlBfPanel) findComponentInRoot("graphPanel");
		}
		return graphPanel;
	}
	protected HtmlGraphDraw getGraphDraw1() {
		if (graphDraw1 == null) {
			graphDraw1 = (HtmlGraphDraw) findComponentInRoot("graphDraw1");
		}
		return graphDraw1;
	}
	protected HtmlGraphDrawData getGraphDrawData1() {
		if (graphDrawData1 == null) {
			graphDrawData1 =
				(HtmlGraphDrawData) findComponentInRoot("graphDrawData1");
		}
		return graphDrawData1;
	}
	protected HtmlGraphDrawLabels getGraphDrawLabels1() {
		if (graphDrawLabels1 == null) {
			graphDrawLabels1 =
				(HtmlGraphDrawLabels) findComponentInRoot("graphDrawLabels1");
		}
		return graphDrawLabels1;
	}
	protected HtmlBfPanel getWebServicesPanel() {
		if (webServicesPanel == null) {
			webServicesPanel =
				(HtmlBfPanel) findComponentInRoot("webServicesPanel");
		}
		return webServicesPanel;
	}
	protected HtmlInputText getSb1() {
		if (sb1 == null) {
			sb1 = (HtmlInputText) findComponentInRoot("sb1");
		}
		return sb1;
	}
	protected HtmlOutputText getSym1() {
		if (sym1 == null) {
			sym1 = (HtmlOutputText) findComponentInRoot("sym1");
		}
		return sym1;
	}
	protected HtmlOutputText getPr1() {
		if (pr1 == null) {
			pr1 = (HtmlOutputText) findComponentInRoot("pr1");
		}
		return pr1;
	}
	protected HtmlOutputText getChg1() {
		if (chg1 == null) {
			chg1 = (HtmlOutputText) findComponentInRoot("chg1");
		}
		return chg1;
	}
	protected HtmlOutputText getPc1() {
		if (pc1 == null) {
			pc1 = (HtmlOutputText) findComponentInRoot("pc1");
		}
		return pc1;
	}
	protected HtmlOutputText getVol1() {
		if (vol1 == null) {
			vol1 = (HtmlOutputText) findComponentInRoot("vol1");
		}
		return vol1;
	}
	protected HtmlCommandExButton getLowerTabbedPanel_back() {
		if (lowerTabbedPanel_back == null) {
			lowerTabbedPanel_back =
				(HtmlCommandExButton) findComponentInRoot("lowerTabbedPanel_back");
		}
		return lowerTabbedPanel_back;
	}
	protected HtmlCommandExButton getLowerTabbedPanel_next() {
		if (lowerTabbedPanel_next == null) {
			lowerTabbedPanel_next =
				(HtmlCommandExButton) findComponentInRoot("lowerTabbedPanel_next");
		}
		return lowerTabbedPanel_next;
	}
	protected HtmlCommandExButton getLowerTabbedPanel_finish() {
		if (lowerTabbedPanel_finish == null) {
			lowerTabbedPanel_finish =
				(HtmlCommandExButton) findComponentInRoot("lowerTabbedPanel_finish");
		}
		return lowerTabbedPanel_finish;
	}
	protected HtmlCommandExButton getLowerTabbedPanel_cancel() {
		if (lowerTabbedPanel_cancel == null) {
			lowerTabbedPanel_cancel =
				(HtmlCommandExButton) findComponentInRoot("lowerTabbedPanel_cancel");
		}
		return lowerTabbedPanel_cancel;
	}
	/** 
	* @author WebSphere Studio
	* @beanName root
	* @managed-bean true
	* @beanClass com.ibm.odcb.tutorial.businessobjects.Root
	*/
	public Root getRoot() {
		if (root == null) {
			root = new Root();
			root =
				(Root) getFacesContext()
					.getApplication()
					.createValueBinding("#{root}")
					.getValue(getFacesContext());
		}
		return root;
	}
	public void setRoot(Root root) {
		this.root = root;
	}
	public String doUpperTabbedPanel_finishAction() {
		// Type Java code to handle command event here
		// Note, this code must return an object of type String (or null)
		String diff = getRootDataModel().getDiffStr();
		Root R = (Root) getRootDataModel().getRootObject();
		R.Synch(diff);
		return "success";
	}
}