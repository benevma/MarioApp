



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import api.restbean.ChartBean;
import api.restbean.InputBean;
import api.restbean.InputExpertBean;
import api.restbean.OutputExpertBean;
import api.ws.rest.util.ClientRestUtils;

/**
 * Servlet implementation class ProvaServlet
 */
@WebServlet(urlPatterns={"/MarioApp/APIServlet"})
@MultipartConfig
public class APIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public APIServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InputExpertBean in = new InputExpertBean();
		in.setUpdate(request.getParameter("update")!=null);

        //Get all doc-i input file contents
        ArrayList<InputBean> array = new ArrayList<InputBean>();
        
		for(int i=1;i<11;i++){
			
			Part doc = request.getPart("doc"+i);
			
	        
			if(doc!=null && doc.getSize()>0){
		        InputStream fileContent = doc.getInputStream();
		        ByteArrayOutputStream result = new ByteArrayOutputStream();
		        byte[] buffer = new byte[1024];
		        int length;
		        while ((length = fileContent.read(buffer)) != -1) {
		            result.write(buffer, 0, length);
		        }
		        System.out.println(result.toString("UTF-8"));
		        InputBean input = new InputBean();
		        input.setName("doc"+i);
		        input.setContent(result.toString("UTF-8"));
		        array.add(input);
			}
			       
		}

		InputBean[] arrayF = array.toArray(new InputBean[array.size()]);
		
        in.setDocuments(arrayF);
        
        //Invoke rest service
        OutputExpertBean invocation = ClientRestUtils.invoke(in,OutputExpertBean.class);
        
        if(invocation==null){
        	//Error
    		response.sendRedirect(request.getContextPath().concat("/error.jsp"));
        }
        else{
            HttpSession session = request.getSession(true);
            
            ArrayList<ChartBean> totalCategories = new ArrayList<ChartBean>();
            //Create arrayCategories
            for(OutputBean out : invocation.getValue()){
            	totalCategories.addAll(out.getCategories());
            }

	    HashMap<String, ChartBean> chartbeans = new HashMap<String, ChartBean>();
			
            //return only one category if duplicate
            for(ChartBean chart : totalCategories){
            	if(!chartbeans.containsKey(chart.getType())){
            		chartbeans.put(chart.getType(), chart);
            	}else{
            		chartbeans.get(chart.getType()).addCount(chart.getCount());
            	}
            }
            
	    ArrayList<ChartBean> catArray = new ArrayList<ChartBean>();
	    for(String key : chartbeans.keySet()){
		catArray.add(chartbeans.get(key));
	    }
			
            //put result in session for jsp displaying
            session.setAttribute("result", catArray);
            
    	    response.sendRedirect(request.getContextPath().concat("/result.jsp"));
          }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
		
}
