package com.app.law;

import com.app.law.dto.user.*;
import com.app.law.entity.User;
import com.app.law.mapper.UserMapperCustom;
import com.app.law.repository.UserRepository;
import com.google.gson.Gson;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LawApplicationTests {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository ur;

	@Test
	public void contextLoads() throws Exception {
		Gson gson = new Gson();
		UserDto dto = new UserDto();

		dto.setEmail("quan@gmail.com");
		dto.setName("Vũ Văn Lợi");
		dto.setPassword(passwordEncoder.encode("12345"));
		File file =new File("a.jpg");
		//dto.setImage(encodeFileToBase64Binary(file));
		dto.setField("Kinh tế , Hôn nhân");
//		dto.setCharges("34.000.00đ - 70.000.00đ");

		InforDetail detail1 = new InforDetail();
		detail1.setCertificate("Chứng chỉ luật sư ");
		detail1.setPlaceOfIssue("Liên đoàn luật sư Việt Nam");
		detail1.setYearOfIssue("2018");

		InforDetail detail2 = new InforDetail();
		detail2.setCertificate("Thạc sỹ luật");
		detail2.setPlaceOfIssue("Đại học Ngoại Thương");
		detail2.setYearOfIssue("2016-2018");

		InforDetail[] inforDetails = {detail1 , detail2};
		dto.setInforDetails(inforDetails);

		Prize prize1 = new Prize();
		prize1.setPrizeName("THOMSON REUTERS");
		prize1.setDescription(" Thomson Reuters đề cử SBLAW là một trong những hãng luật hàng đầu trong lĩnh vực tư vấn sở hữu trí tuệ tại Việt Nam.");

		Prize prize2 = new Prize();
		prize2.setPrizeName("Legal 500");
		prize2.setDescription("Từ năm 2012 đến năm 2016, SBLAW liên tiếp nhận được các giải thưởng từ The Legal 500 cho hoạt động tư vấn doanh nghiệp và sở hữu trí tuệ.The Legal 500 là một trong những tổ chức uy tín toàn cầu có chức năng đánh giá và xếp hạng các công ty luật tại các quốc gia thành viên.");

		Prize prize3 = new Prize();
		prize3.setPrizeName("IFLR1000");
		prize3.setDescription("SBLAW đã vinh dự được ASIALAW Profiles, một tổ chức uy tín về xếp hạng các công ty luật tại Châu Á Thái Bình Dương, trao giải thưởng về hoạt động tư vấn pháp luật tại Việt Nam. ");

		Prize prize4 = new Prize();
		prize4.setPrizeName("IFLR1000");
		prize4.setDescription("IFLR1000 (The Guide to the World’s Leading Financial Law Firms) đã đề cử SBLAW là hãng luật có kinh nghiệm chuyên sâu trong lĩnh vực tư vấn đầu tư và tài chính ngân hàng.");

		dto.setPrizes(new Prize[] {prize1 , prize2 , prize3 , prize4});

		Experience experience1 = new Experience();
		experience1.setCompany("Luật Danh Việt");
		experience1.setPlace("Hà Nội");
		experience1.setTime("2016-Now");

		Experience experience2 = new Experience();
		experience2.setCompany("Luật Vũ Hà");
		experience2.setPlace("Hà Nội");
		experience2.setTime("2012-2016");

		dto.setExperiences(new Experience[] {experience1 , experience2});

		Education education = new Education();
		education.setLevel("Cư Nhân");
		education.setPlace("Đại học luật Hà Nội");
		education.setTime("2012-2016");

		dto.setEducations(new Education[] {education});

		dto.setRoleId(1);

		System.out.println(gson.toJson(dto));

//		User user = UserMapperCustom.dtoToEntity(dto);
//		user.setRoleId(1);
//		User createdUser = null;
//		createdUser = ur.saveAndFlush(user);



	}

	private static String encodeFileToBase64Binary(File file) throws Exception{
		FileInputStream fileInputStreamReader = new FileInputStream(file);
		byte[] bytes = new byte[(int)file.length()];
		fileInputStreamReader.read(bytes);
		return new String(Base64.encodeBase64(bytes), "UTF-8");
	}

}
