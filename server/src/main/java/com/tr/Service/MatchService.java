package com.tr.Service;

import com.tr.DAO.CompanyDao;
import com.tr.Model.Company;
import com.tr.Model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchService {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private PositionService positionService;

	public  String match(String sentence){
		// user input
		// 我要找工作 configure by hand
		// {job.career: [职务，职位，工作，...]}
		sentence = sentence.toLowerCase();

		HashMap<String, String[]> dictionary = new HashMap<String, String[]>(); 

		String[] strArray1 = new String[]{"行业","领域","干什么","做什么","哪一行","业务","行当","产业"};
		dictionary.put("company.industry", strArray1);
		String[] strArray2 = new String[]{"城市","诚实","成事","省份","哪个省","什么省","什么城市","哪个城市","都会","都市"};
		dictionary.put("company.city", strArray2);
		String[] strArray3 = new String[]{"地址","地方","地点","哪儿","在哪","地方","所在","位置","方位" };
		dictionary.put("company.address", strArray3);
		String[] strArray4 = new String[]{"规模","多少人","人数","多大","多少员工","员工数" };
		dictionary.put("company.scale", strArray4);
		String[] strArray5 = new String[]{"融资","几轮","哪一轮","资金","财务","金融","集资","筹集","投资"};
		dictionary.put("company.process", strArray5);
		String[] strArray6 = new String[]{"简介","介绍","描述","形容","基本情况","大概情况","大致情况","描绘","情况","状况","概况","了解"};
		dictionary.put("company.description", strArray6);
		
		String[] pstrArray1 = new String[]{"ui","web","java","c++","c#","python","php","html","css","js","ios","android","linux","unix","c语言","嵌入式","物联网","云计算","数据","安卓","算法","设计","前端","后端","测试","产品","技术支持","运维","开发","研发","客服","项目经理","架构师","cto","销售","售前","售后"};
		dictionary.put("position.", pstrArray1);

		HashSet<String> matchedColumns = new HashSet<>();
		
		for (int i = 0; i < sentence.length(); ++i) {
			for (Map.Entry<String, String[]> column : dictionary.entrySet()) {
				for (String word : column.getValue()) {
					if(sentence.length()-i>=word.length()){
						if (sentence.substring(i, i + word.length()).equals(word)) {
							if(column.getKey().charAt(0)=='c'){
								matchedColumns.add(column.getKey());
							}else{
								matchedColumns.add("position." + word);
							}
						}
					}
				}
			}
		}
		
		if (matchedColumns.isEmpty()) {
			// process...
			return "not found";
		} else{
			StringBuilder sb = new StringBuilder();
			// output...
			for(String s: matchedColumns){
				sb.append(s);
				sb.append("  ");
			}
			return sb.toString();
		}
	}
	public  void main(String[] args) {
		// TODO Auto-generated method stub
		String result1 = match("公司地址在哪里");
		System.out.println("输入：公司地址在哪里");
		System.out.println("输出："+result1);
		System.out.println();
		String result2 = match("在哪个城市");
		System.out.println("输入：在哪个城市");
		System.out.println("输出："+result2);
		System.out.println();
		String result3 = match("有java或Python的前端或者后端岗位吗");
		System.out.println("输入：有java或Python的前端或者后端岗位吗");
		System.out.println("输出："+result3);
		System.out.println();
		String result4 = match("公司属于哪个行业");
		System.out.println("输入：公司属于哪个行业");
		System.out.println("输出："+result4);
		System.out.println();
		String result5 = match("公司有多少员工");
		System.out.println("输入：公司有多少员工");
		System.out.println("输出："+result5);
		System.out.println();
		String result6 = match("公司大概情况介绍下");
		System.out.println("输入：公司大概情况介绍下");
		System.out.println("输出："+result6);
		System.out.println();
		String result7 = match("有公司简介吗");
		System.out.println("输入：有公司简介吗");
		System.out.println("输出："+result7);
		System.out.println();
		String result8 = match("公司进行到哪一轮融资了");
		System.out.println("输入：公司进行到哪一轮融资了");
		System.out.println("输出："+result8);
		System.out.println();
		String result9 = match("地址在哪里");
		System.out.println("输入：地址在哪里");
		System.out.println("输出："+result9);
		System.out.println();
		String result10 = match("有HTML前端或CSS岗位吗");
		System.out.println("输入：有HTML前端或CSS岗位吗");
		System.out.println("输出："+result10);
		System.out.println();
	}

    public List<String> getCompanyInfo(String[] fields, String companyId){
        Company company = this.companyService.get(companyId);
        ArrayList list = new ArrayList();

        for(int i=0; i<fields.length; i++){
            String field = fields[i];
            switch (field){
                case "industry":
                    list.add("本公司属于"+company.getIndustry());
                    break;
                case "city":
                    list.add("本公司位于"+company.getCity());
                    break;
                case "address":
                    list.add("地址为："+company.getAddress());
                    break;
                case "scale":
                    list.add("目前规模为"+company.getScale());
                    break;
                case "process":
                    list.add("当前融资进展为："+company.getProcess());
                    break;
                case "description":
                    list.add(company.getDescription());
                    break;
                default:
                    list.add("");
                    break;
            }
        }

        return list;
    }

    public List<Position> getPositions(String[] tags, String companyId){
        return this.positionService.filterByTags(companyId,tags);
    }
}
