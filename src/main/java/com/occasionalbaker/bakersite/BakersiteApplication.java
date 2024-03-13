package com.occasionalbaker.bakersite;

import com.occasionalbaker.bakersite.backend.entity.CakeCatalogue;
import com.occasionalbaker.bakersite.backend.entity.Order;
import com.occasionalbaker.bakersite.backend.entity.Role;
import com.occasionalbaker.bakersite.backend.entity.User;
import com.occasionalbaker.bakersite.backend.repository.CakeCatalogueRepository;
import com.occasionalbaker.bakersite.backend.repository.OrderRepository;
import com.occasionalbaker.bakersite.backend.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SpringBootApplication
@ComponentScan("com.occasionalbaker.bakersite")
public class BakersiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(BakersiteApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	CakeCatalogueRepository cakeCatalogueRepository;

	public BakersiteApplication(UserService userService) {
		this.userService = userService;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void addUser() throws IOException {
		User user = new User("Tobechi","Okaro","08178676174","tobechiokaro2013@gmail.com",passwordEncoder.encode("Tobechi@2020"));
		User user1 = new User("Obiageli","Okaro","08024138996","oby@gmail.com",passwordEncoder.encode("Tobechi@2020"));
		user.setRole(Role.USER);
		user1.setRole(Role.ADMIN);
		userService.saveUser(user);
		userService.saveUser(user1);

		Date date = new Date();

		SimpleDateFormat stf = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		//added date and time to constructor parameters
		Order order = new Order(user.getUsername(),"100004333","chocolate cake","slightly darker than normal","235 royal avenue Gwarimpa",sdf.format(date), stf.format(date));

		Order order1 = new Order(user.getUsername(),"100004333","chocolate cake","slightly darker than normal","235 royal avenue Gwarimpa",sdf.format(date),stf.format(date));
		orderRepository.save(order);
		orderRepository.save(order1);
		File file = new File("C:\\Users\\tobec\\SpringProjects\\images\\Chocolate-Cake.jpg");

		File nfile = new File("C:\\Users\\tobec\\SpringProjects\\images\\vanilla_cake.jpg");


		System.out.println("file length is" + file.length());
		System.out.println("compressed file length is"+compress(file).length());

		File compressedFile = compress(file);
		File ncompressedFile = compress(nfile);
//		compression algo
		byte[]fileContent = Files.readAllBytes(compressedFile.toPath());
		byte[]nfileContent = Files.readAllBytes(ncompressedFile.toPath());



		CakeCatalogue cakeCatalogue = new CakeCatalogue("Chocolate Cake","500","a rich moist chocolate cake, capable of putting you on a flavour highway blah blah blah",fileContent);

		CakeCatalogue ncakeCatalogue = new CakeCatalogue("Vanilla Cake","500","rich moist vanilla cake",nfileContent);

		ncakeCatalogue.setAvailable(true);
		cakeCatalogue.setAvailable(true);
		cakeCatalogueRepository.save(cakeCatalogue);
		cakeCatalogueRepository.save(ncakeCatalogue);

		compressedFile.delete();



		Set<Order> orderSet= new HashSet<>();
		orderSet.add(order);
		orderSet.add(order1);


		user.setOrder(orderSet);

		System.out.println(user.getOrder());
	}

	public File compress(File originalFile) throws IOException {

		BufferedImage image = ImageIO.read(originalFile);
		File compressedImage = new File(originalFile.getName());

		OutputStream os = new FileOutputStream(compressedImage);

		Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(FilenameUtils.getExtension(originalFile.getPath()));
		ImageWriter writer = (ImageWriter)writers.next();

		ImageOutputStream ios = ImageIO.createImageOutputStream(os);
		writer.setOutput(ios);

		ImageWriteParam param = writer.getDefaultWriteParam();

		param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		param.setCompressionQuality(0.2f);  // Change the quality value you prefer
		writer.write(null, new IIOImage(image, null, null), param);

		os.close();
		ios.close();
		writer.dispose();

		return compressedImage;
	}


}
