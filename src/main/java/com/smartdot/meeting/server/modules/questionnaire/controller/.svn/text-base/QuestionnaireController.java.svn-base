package com.smartdot.meeting.server.modules.questionnaire.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartdot.meeting.server.common.entity.Questionnaire;
import com.smartdot.meeting.server.common.entity.QuestionnaireOption;
import com.smartdot.meeting.server.common.entity.QuestionnaireTopic;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.questionnaire.model.QuestionnaireOptionVO;
import com.smartdot.meeting.server.modules.questionnaire.model.QuestionnaireTopicVO;
import com.smartdot.meeting.server.modules.questionnaire.service.IQuestionnaireOptionService;
import com.smartdot.meeting.server.modules.questionnaire.service.IQuestionnaireService;
import com.smartdot.meeting.server.modules.questionnaire.service.IQuestionnaireTopicService;

/**
 * 
 * @author yanjj
 * @since 2017.1.19
 *
 */
@Controller
@RequestMapping(value = "/questionnaire")
public class QuestionnaireController {

	private static final Logger _LOG = Logger.getLogger(QuestionnaireController.class);

	public static final String SUCCESS_MESSAGE = "操作成功";

	@Resource(name = IQuestionnaireService.SERVICE_NAME)
	private IQuestionnaireService questionnaireService;

	@Resource(name = IQuestionnaireTopicService.SERVICE_NAME)
	private IQuestionnaireTopicService topicService;

	@Resource(name = IQuestionnaireOptionService.SERVICE_NAME)
	private IQuestionnaireOptionService optionService;

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/questionnaire/questionnaireList";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryList")
	@ResponseBody
	public Map<String, Object> queryList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		String title = request.getParameter("title");
		if (StringUtils.isNotBlank(title)) {
			searchMap.put("title", title);
		}
		searchMap.put("enable", true);
		return questionnaireService.pageList(PageUtilExtent.getPageInfo(request), searchMap);
	}

	@RequestMapping(value = "/questionnaireAdd")
	public String questionnaireAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/questionnaire/questionnaireAdd";
	}

	@RequestMapping(value = "/questionnaireAddSave", produces = { "application/json" }, method = RequestMethod.POST)
	@ResponseBody
	public String questionnaireAddSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String title = request.getParameter("title");
		String description = request.getParameter("description");

		Questionnaire questionnaire = new Questionnaire();
		questionnaire.setTitle(title);
		questionnaire.setDescription(description);
		questionnaire.setState(0);
		String topics = request.getParameter("topics");
		if (StringUtils.isNotBlank(topics)) {// A&t1 # B&t2|o1,o2 # C&t3|o1,o2,o3 # D&t4 ,
			String[] topicsArray = topics.split("#");
			questionnaire.setTopicNum(topicsArray.length);

			boolean result = false;
			if (StringUtils.isBlank(questionnaire.getId())) {
				result = questionnaireService.save(questionnaire);
			}

			if (result) {
				for (int i = 0; i < topicsArray.length; i++) {
					QuestionnaireTopic topicObj = new QuestionnaireTopic();
					String topicType = topicsArray[i].split("&")[0];
					String topic;
					if (topicsArray[i].split("&")[1].split("\\|").length > 0) {
						topic = topicsArray[i].split("&")[1].split("\\|")[0];
					} else {
						topic = topicsArray[i].split("&")[1];
					}

					topicObj.setTopic(topic);
					topicObj.setTopicType(topicType);
					topicObj.setTopicNumber(String.valueOf(i + 1));
					topicObj.setQuestionnaireId(questionnaire.getId());

					if (StringUtils.isBlank(topicObj.getId())) {
						result = topicService.save(topicObj);
					}

					if (result) {
						if (topicsArray[i].split("\\|").length > 1) {
							String[] optionsArray = topicsArray[i].split("\\|")[1].split(",");
							for (int j = 0; j < optionsArray.length; j++) {
								QuestionnaireOption optionObj = new QuestionnaireOption();
								String content = optionsArray[j];
								optionObj.setContent(content);
								optionObj.setQuestionnaireId(questionnaire
										.getId());
								optionObj.setTopicId(topicObj.getId());

								if (StringUtils.isBlank(optionObj.getId())) {
									optionService.save(optionObj);
								}
							}
						}
					}
				}
			}

		}
		return "redirect:/dispatcher/questionnaire/index";
	}

	@RequestMapping(value = "/questionnaireEdit")
	public String questionnaireEdit(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String questionnaireId = request.getParameter("questionnaireId");
		if (StringUtils.isBlank(questionnaireId)) {
			return null;
		}
		model.addAttribute("questionnaireId", questionnaireId);
		return "/questionnaire/questionnaireEdit";
	}

	@RequestMapping(value = "/questionnaireFindById")
	@ResponseBody
	public Map<String, Object> questionnaireFindById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String questionnaireId = request.getParameter("questionnaireId");
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isBlank(questionnaireId)) {
			return null;
		}
		Questionnaire questionnaire = questionnaireService.getQuestionnaireById(questionnaireId);
		map.put("id", questionnaire.getId());
		map.put("title", questionnaire.getTitle());
		map.put("description", questionnaire.getDescription());
		map.put("state", questionnaire.getState());

		List<QuestionnaireTopicVO> topisVOList = new ArrayList<QuestionnaireTopicVO>();

		List<QuestionnaireTopic> topisList = topicService.findAllByProperty("QUESTIONNAIREID", questionnaire.getId());

		Map<String, Object> optionMaps = new HashMap<String, Object>();
		optionMaps.put("QUESTIONNAIREID", questionnaire.getId());

		for (QuestionnaireTopic questionnaireTopic : topisList) {
			optionMaps.put("TOPICID", questionnaireTopic.getId());
			List<QuestionnaireOption> optionList = optionService.findAllByProperties(optionMaps);

			List<QuestionnaireOptionVO> optionVOList = new ArrayList<QuestionnaireOptionVO>();

			for (QuestionnaireOption questionnaireOption : optionList) {
				QuestionnaireOptionVO optionVO = new QuestionnaireOptionVO();
				optionVO.setId(questionnaireOption.getId());
				optionVO.setContent(questionnaireOption.getContent());
				optionVO.setQuestionnaireId(questionnaireOption.getQuestionnaireId());
				optionVO.setTopicId(questionnaireOption.getTopicId());
				optionVOList.add(optionVO);
			}
			QuestionnaireTopicVO topicVO = new QuestionnaireTopicVO();
			topicVO.setId(questionnaireTopic.getId());
			topicVO.setTopic(questionnaireTopic.getTopic());
			topicVO.setNotes(questionnaireTopic.getNotes());
			topicVO.setTopicNumber(questionnaireTopic.getTopicNumber());
			topicVO.setQuestionnaireId(questionnaireTopic.getQuestionnaireId());
			topicVO.setTopicType(questionnaireTopic.getTopicType());
			topicVO.setOptions(optionVOList);
			topisVOList.add(topicVO);
		}

		map.put("topics", topisVOList);
		return map;
	}

	@RequestMapping(value = "/questionnaireEditSave", produces = { "application/json" }, method = RequestMethod.POST)
	@ResponseBody
	public String questionnaireEditSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String description = request.getParameter("description");

		Questionnaire questionnaire = new Questionnaire();
		questionnaire.setId(id);
		questionnaire.setTitle(title);
		questionnaire.setDescription(description);
		questionnaire.setState(0);
		String topics = request.getParameter("topics");
		if (StringUtils.isNotBlank(topics)) {// A&t1 # B&t2|o1,o2 # C&t3|o1,o2,o3 # D&t4 ,
			String[] topicsArray = topics.split("#");
			questionnaire.setTopicNum(topicsArray.length);

			boolean result = false;
			if (!StringUtils.isBlank(id)) {
				result = questionnaireService.updateQuestionnaire(questionnaire);
			}

			if (result) {
				List<QuestionnaireTopic> topicList = topicService.findAllByProperty("QUESTIONNAIREID", id);
				List<String> ids = new ArrayList<String>();
				for (QuestionnaireTopic questionnaireTopic : topicList) {
					ids.add(questionnaireTopic.getId());
				}
				result = topicService.deleteAll(ids);
				
				if (result) {
					List<QuestionnaireOption> optionList = optionService.findAllByProperty("QUESTIONNAIREID", id);
					List<String> ids1 = new ArrayList<String>();
					for (QuestionnaireOption questionnaireOption : optionList) {
						ids1.add(questionnaireOption.getId());
					}
					result = optionService.deleteAll(ids1);
				}

				if (result) {
					for (int i = 0; i < topicsArray.length; i++) {
						QuestionnaireTopic topicObj = new QuestionnaireTopic();
						String topicType = topicsArray[i].split("&")[0];
						String topic = "";
						if (topicsArray[i].split("&")[1].split("\\|").length > 0) {
							topic = topicsArray[i].split("&")[1].split("\\|")[0];
						} else {
							topic = topicsArray[i].split("&")[1];
						}

						topicObj.setTopic(topic);
						topicObj.setTopicType(topicType);
						topicObj.setTopicNumber(String.valueOf(i + 1));
						topicObj.setQuestionnaireId(questionnaire.getId());

						if (StringUtils.isBlank(topicObj.getId())) {
							result = topicService.save(topicObj);
						}

						if (result) {
							if (topicsArray[i].split("\\|").length > 1) {
								String[] optionsArray = topicsArray[i].split("\\|")[1].split(",");
								for (int j = 0; j < optionsArray.length; j++) {
									QuestionnaireOption optionObj = new QuestionnaireOption();
									String content = optionsArray[j];
									optionObj.setContent(content);
									optionObj.setQuestionnaireId(questionnaire.getId());
									optionObj.setTopicId(topicObj.getId());

									if (StringUtils.isBlank(optionObj.getId())) {
										optionService.save(optionObj);
									}
								}
							}
						}
					}
				}
			}
		}
		return "redirect:/dispatcher/questionnaire/index";
	}

	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		Questionnaire questionnaire = questionnaireService.getQuestionnaireById(id);
		if (questionnaire != null) {
			questionnaireService.deleteById(id);
		}

		List<QuestionnaireTopic> topicList = topicService.findAllByProperty("QUESTIONNAIREID", id);
		List<String> ids = new ArrayList<String>();
		for (QuestionnaireTopic questionnaireTopic : topicList) {
			ids.add(questionnaireTopic.getId());
		}
		topicService.deleteAll(ids);

		List<QuestionnaireOption> optionList = optionService.findAllByProperty("QUESTIONNAIREID", id);
		List<String> ids1 = new ArrayList<String>();
		for (QuestionnaireOption questionnaireOption : optionList) {
			ids1.add(questionnaireOption.getId());
		}
		optionService.deleteAll(ids1);

		return "redirect:/dispatcher/questionnaire/index";
	}
}
