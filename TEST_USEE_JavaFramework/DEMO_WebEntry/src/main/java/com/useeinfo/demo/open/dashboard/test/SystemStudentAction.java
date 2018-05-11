package com.useeinfo.demo.open.dashboard.test;



import com.useeinfo.demo.modules.test.biz.SystemStudentBiz;
import com.useeinfo.demo.modules.test.entity.SystemStudent;
import com.useeinfo.framework.extend.action.BaseAction;
import com.useeinfo.framework.sugar.data.QueryParam;
import com.useeinfo.framework.sugar.tools.StringConverters;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/web/admin/systemStudent")
public class SystemStudentAction extends BaseAction{

    @Autowired
    private SystemStudentBiz systemStudentBiz;

    /**
     * 打开列表页面
     */
    @RequestMapping("/getSystemStudentListPage")
    public ModelAndView getSystemStudentListPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/SystemStudent/systemStudentList");

        return modelAndView;
    }
    /**
     * 分页获取JSON数据
     */
    @RequestMapping("/getSystemStudentListJSON")
    @ResponseBody
    public JSONObject getSystemStudentListJSON(@RequestParam(value = "page", required = false) String pageNowParam,
                                            @RequestParam(value = "rows", required = false) String pageSizeParam) {

        QueryParam queryParam = new QueryParam(pageNowParam, pageSizeParam, 0);
        return systemStudentBiz.findJSONList(queryParam);
    }

    /**
     * 获取编辑页面
     */
    @RequestMapping("/getSystemStudentEditPage")
    public ModelAndView getSystemStudentEditPage(@RequestParam(value = "studentId", required = false) String studentIdParam) {

        Long studentId = StringConverters.ToLong(studentIdParam);

        SystemStudent systemStudent = null;
        if (studentId != null) {
            systemStudent = systemStudentBiz.findModel(studentId);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/SystemStudent/systemStudentEditPart");
        modelAndView.addObject("systemStudent", systemStudent);
        return modelAndView;
    }

    /**
     * 获取详情页面
     */
    @RequestMapping("/getSystemStudentViewPage")
    public ModelAndView getSystemStudentViewPage(@RequestParam(value = "studentId", required = false) String studentIdParam) {

        Long studentId = StringConverters.ToLong(studentIdParam);

        SystemStudent systemStudent = null;
        if (studentId != null) {
            systemStudent = systemStudentBiz.findModel(studentId);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/systemStudent/systemStudentViewPart");
        modelAndView.addObject("systemStudent", systemStudent);
        return modelAndView;
    }

    /**
     * 执行提交的新增或修改请求
     */
    @RequestMapping(value = "/executeSystemStudentEdit", produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String executeSystemStudentEdit(SystemStudent systemStudent) {

//        //判断角色名是否重复
//        if (systemStudentBiz.existStudentName(systemStudent)) {
//            return "您输入的角色名为空或已存在，请重新输入";
//        }

        systemStudentBiz.addOrUpdate(systemStudent);
        return "1";
    }


}
