package com.example.servicemanagementsystem.services.client;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.servicemanagementsystem.Entity.Ad;
import com.example.servicemanagementsystem.Entity.Reservation;
import com.example.servicemanagementsystem.Entity.User;
import com.example.servicemanagementsystem.Repository.AdRepository;
import com.example.servicemanagementsystem.Repository.ReservationRepository;
import com.example.servicemanagementsystem.Repository.UserRepository;
import com.example.servicemanagementsystem.dto.AdDTO;
import com.example.servicemanagementsystem.dto.AdDetailsForClientDTO;
import com.example.servicemanagementsystem.dto.ReservationDTO;
import com.example.servicemanagementsystem.enums.ReservationStatus;
import com.example.servicemanagementsystem.enums.ReviewStatus;

@Service
public class ClientServiceImpl implements ClientService {
    
        @Autowired
        private AdRepository adRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private ReservationRepository reservationRepository;

        public List<AdDTO> getAllAds(){

            return adRepository.findAll().stream().map(Ad::getAdDto).collect(Collectors.toList());
        }

        public List<AdDTO> searchAdByName(String name){
            return adRepository.findAllByServiceNameContaining(name).stream().map(Ad::getAdDto).collect(Collectors.toList());
        }
        public List<AdDTO> searchAdByCategory(String category){
                return adRepository.findAllByCategory(category).stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

        public boolean bookService(ReservationDTO reservationDTO){
            Optional<Ad> optionalAd = adRepository.findById(reservationDTO.getAdId());
            Optional<User> optionalUser = userRepository.findById(reservationDTO.getUserId());

            if(optionalAd.isPresent() && optionalUser.isPresent()){
                Reservation reservation = new Reservation();
                reservation.setBookDate(reservationDTO.getBookDate());
                reservation.setReservationStatus(ReservationStatus.PENDING);
                reservation.setUser((optionalUser.get()));
                reservation.setAd(optionalAd.get());
                reservation.setCompany(optionalAd.get().getUser());
                reservation .setReviewStatus((ReviewStatus.FALSE));

                reservationRepository.save(reservation);
                return true;
            }
            return false;
        }

        public AdDetailsForClientDTO getAdDetailsForClientDTO(Long adId){
            Optional <Ad> optionalAd = adRepository.findById((adId));
            AdDetailsForClientDTO adDetailsForClientDTO = new AdDetailsForClientDTO();
            if(optionalAd.isPresent()){
                adDetailsForClientDTO.setAdDTO(optionalAd.get().getAdDto());

            }
            return adDetailsForClientDTO;
        }

        public List<ReservationDTO> getAllBookingsByUserId(Long userId){
            return reservationRepository.findAllByUserId(userId).stream().map(Reservation::getReservationDTO).collect(Collectors.toList());
      }
}












