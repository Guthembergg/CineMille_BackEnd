package com.cineMille.runner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cineMille.auth.entity.ERole;
import com.cineMille.auth.entity.Role;
import com.cineMille.auth.entity.User;
import com.cineMille.auth.repository.RoleRepository;
import com.cineMille.auth.repository.UserRepository;
import com.cineMille.auth.service.AuthService;
import com.cineMille.auth.service.AuthServiceImpl;
import com.cineMille.model.Sala;
import com.cineMille.model.TipoSala;
import com.cineMille.service.SalaService;

@Component
public class SalaRunner implements ApplicationRunner {
	@Autowired
	SalaService serv;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		if (serv.findAllSala().isEmpty()) {
			System.out.println("Sala Runner...");
			List<Sala> sale = new ArrayList<Sala>();
			Sala s1 = new Sala("sala standard", TipoSala.STANDARD, 50);
			Sala s2 = new Sala("sala Imax", TipoSala.IMAX, 250);
			Sala s3 = new Sala( "sala Dolby", TipoSala.DOLBY, 150);
			Sala s4 = new Sala( "sala Imax2", TipoSala.IMAX, 200);
			Sala s5 = new Sala( "sala Dolby2", TipoSala.DOLBY, 150);
			Sala s6 = new Sala( "sala standard2", TipoSala.STANDARD, 100);
			Sala s7 = new Sala( "sala standard3", TipoSala.STANDARD, 90);
			Sala s8 = new Sala( "sala Imax3", TipoSala.IMAX, 70);
			Sala s9 = new Sala( "sala Dolby3", TipoSala.DOLBY, 160);
			Sala s10 = new Sala( "sala Imax4", TipoSala.IMAX, 220);
			Sala s11 = new Sala( "sala Dolby4", TipoSala.DOLBY, 80);
			Sala s12 = new Sala( "sala standard4", TipoSala.STANDARD, 130);
			sale.add(s1);
			sale.add(s2);
			sale.add(s3);
			sale.add(s4);
			sale.add(s5);
			sale.add(s6);
			sale.add(s7);
			sale.add(s8);
			sale.add(s9);
			sale.add(s10);
			sale.add(s11);
			sale.add(s12);
			serv.addAllSala(sale);
		}
	}

}
