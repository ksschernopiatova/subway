package com.solvd.subway.persistence.Impl;

import com.solvd.subway.domain.*;
import com.solvd.subway.persistence.MybatisSessionHolder;
import com.solvd.subway.persistence.SubwayRepository;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SubwayMybatisImpl implements SubwayRepository {
    @Override
    public void create(Subway subway) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            SubwayRepository subwayRepository = session.getMapper(SubwayRepository.class);
            subwayRepository.create(subway);
        }
    }


    @Override
    public void update(String city, Long id) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            SubwayRepository subwayRepository = session.getMapper(SubwayRepository.class);
            subwayRepository.update(city, id);
        }
    }

    @Override
    public void delete(Subway subway) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            SubwayRepository subwayRepository = session.getMapper(SubwayRepository.class);
            subwayRepository.delete(subway);
        }
    }

    @Override
    public List<Subway> findEmployees() {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            SubwayRepository subwayRepository = session.getMapper(SubwayRepository.class);
            List<Subway> subways = subwayRepository.findEmployees();
            return subways;
        }
    }

    @Override
    public Subway getById(Long id) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            SubwayRepository subwayRepository = session.getMapper(SubwayRepository.class);
            Subway subway = subwayRepository.getById(id);
            return subway;
        }
    }

    @Override
    public Subway getByCity(String city) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            SubwayRepository subwayRepository = session.getMapper(SubwayRepository.class);
            Subway subway = subwayRepository.getByCity(city);
            return subway;
        }
    }

    @Override
    public Subway getFullSubway() {
        Subway minsk = new Subway();
        minsk.setCity("Minsk");

        PaymentOption ticket = new PaymentOption();
        ticket.setType(Type.TICKET);
        ticket.setPrice(BigDecimal.valueOf(1.0));

        PaymentOption pass = new PaymentOption();
        pass.setType(Type.TRAVEL_CARD);
        pass.setPrice(BigDecimal.valueOf(12));

        List<PaymentOption> minskPaymentOptions = new ArrayList<>();
        minskPaymentOptions.add(ticket);
        minskPaymentOptions.add(pass);
        minsk.setPaymentOptions(minskPaymentOptions);

        Train stadler = new Train();
        stadler.setModel("M110/M111");
        stadler.setSpeed(90);
        stadler.setCreationDate(LocalDate.of(2018, 1, 1));

        Train oka = new Train();
        oka.setModel("Oka");
        oka.setSpeed(115);
        oka.setCreationDate(LocalDate.of(2011, 1, 1));

        List<Train> minskTrains = new ArrayList<>();
        minskTrains.add(stadler);
        minskTrains.add(oka);
        minsk.setTrains(minskTrains);

        Department hr = new Department();
        hr.setTitle("HR");

        Department engineer = new Department();
        engineer.setTitle("Engineer");

        Department security = new Department();
        security.setTitle("Security");

        Address wesAddress = new Address();
        wesAddress.setCity("Minsk");
        wesAddress.setStreet("Vaneeva");
        wesAddress.setHouseNumber(15);

        Employee wes = new Employee();
        wes.setFirstName("Wes");
        wes.setLastName("Long");
        wes.setDob(LocalDate.of(2001, 10, 5));
        wes.setPosition("HR manager");
        wes.setAddress(wesAddress);

        Employee derrick = new Employee();
        derrick.setFirstName("Derrick");
        derrick.setLastName("Jones");
        derrick.setDob(LocalDate.of(1985, 7, 11));
        derrick.setPosition("Head engineer");

        Address roninAddress = new Address();
        roninAddress.setCity("Brest");
        roninAddress.setStreet("Masherova");
        roninAddress.setHouseNumber(26);

        Employee ronin = new Employee();
        ronin.setFirstName("Ronin");
        ronin.setLastName("Torres");
        ronin.setDob(LocalDate.of(1997, 7, 5));
        ronin.setPosition("Engineer");
        ronin.setAddress(roninAddress);

        List<Employee> hrEmployees = new ArrayList<>();
        hrEmployees.add(wes);
        hr.setEmployees(hrEmployees);

        List<Employee> engineerEmployees = new ArrayList<>();
        engineerEmployees.add(derrick);
        engineerEmployees.add(ronin);
        engineer.setEmployees(engineerEmployees);

        List<Department> minskDepartments = new ArrayList<>();
        minskDepartments.add(hr);
        minskDepartments.add(security);
        minskDepartments.add(engineer);
        minsk.setDepartments(minskDepartments);

        Line moskovskaya = new Line();
        moskovskaya.setTitle("Moskovskaya");

        Line avtozavodskaya = new Line();
        avtozavodskaya.setTitle("Avtozavodskaya");

        Station partiz = new Station();
        partiz.setNumber(110);
        partiz.setTitle("Partizanskaya");

        Station nemiga = new Station();
        nemiga.setNumber(127);
        nemiga.setTitle("Nemiga");

        Station malinovka = new Station();
        malinovka.setNumber(111);
        malinovka.setTitle("Malinovka");

        List<Station> moskStations = new ArrayList<>();
        moskStations.add(malinovka);

        List<Station> avtozStations = new ArrayList<>();
        avtozStations.add(nemiga);
        avtozStations.add(partiz);

        moskovskaya.setStations(moskStations);
        avtozavodskaya.setStations(avtozStations);
        List<Line> minskLines = new ArrayList<>();
        minskLines.add(moskovskaya);
        minskLines.add(avtozavodskaya);
        minsk.setLines(minskLines);

        Privilege pupil = new Privilege();
        pupil.setCategory("Pupil");
        pupil.setDiscount(100);

        Privilege retired = new Privilege();
        retired.setCategory("Retired");
        retired.setDiscount(40);

        List<Privilege> minskPrivileges = new ArrayList<>();
        minskPrivileges.add(pupil);
        minskPrivileges.add(retired);

        minsk.setPrivileges(minskPrivileges);
        return minsk;
    }
}
