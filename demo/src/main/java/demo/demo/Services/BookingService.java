package demo.demo.Services;


import demo.demo.Module.Booking;
import demo.demo.Module.Room;
import demo.demo.Module.RoomType;
import demo.demo.Repository.*;
import demo.demo.jsonResponse.BookingRequest;
import demo.demo.jsonResponse.GetBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ServiceRepository serviceRepository;
    private final RoomRepository roomRepository;
    private final RoomRequestRepository roomRequestRepository;
    private final ServiceRequestRepository serviceRequestRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final DiscountRepository discountRepository;
    private final DashboardService dashboardService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository, RoomTypeRepository roomTypeRepository, RoomRequestRepository roomRequestRepository, ServiceRepository serviceRepository, ServiceRequestRepository serviceRequestRepository, DiscountRepository discountRepository,  DashboardService dashboardService) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.roomRequestRepository = roomRequestRepository;
        this.serviceRequestRepository = serviceRequestRepository;
        this.discountRepository = discountRepository;
        this.serviceRepository = serviceRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.dashboardService = dashboardService;
    }

    public int book(UUID bookingID, UUID roomReqNo, int discountNo, UUID serviceReqNo) {

        return bookingRepository.book(bookingID, roomReqNo, discountNo, serviceReqNo);
    }

//    public int addBookRoom(UUID bookingID, UUID roomID) {
//        return bookRoomRepository.addBookRoom(roomID, bookingID);
//    }

    public Room getRoomForBooking(BookingRequest bookingRequest) {
        String roomType = bookingRequest.getRoomType();
        System.out.println(roomType);
        System.out.println(bookingRequest.getHotelId());
        System.out.println(bookingRequest.getCheckInDate());
        System.out.println(bookingRequest.getCheckOutDate());
        if(roomType.equals("deluxe")) {
            List<Room> r = bookingRepository.getRoomDeluxe(bookingRequest.getHotelId(),bookingRequest.getCheckInDate(), bookingRequest.getCheckOutDate());
                System.out.println(r.size());
            return r.get(0);
        }
        else if(roomType.equals("super_deluxe")) {
            List<Room> r = bookingRepository.getRoomSuperDeluxe(bookingRequest.getHotelId(),bookingRequest.getCheckInDate(), bookingRequest.getCheckOutDate());
            return r.get(0);
        }
        else if(roomType.equals("imperial")) {
            List<Room> r = bookingRepository.getRoomImperial(bookingRequest.getHotelId(),bookingRequest.getCheckInDate(), bookingRequest.getCheckOutDate());
            return r.get(0);
        }
        else if(roomType.equals("cottage")){
            List<Room> r = bookingRepository.getRoomCottage(bookingRequest.getHotelId(),bookingRequest.getCheckInDate(), bookingRequest.getCheckOutDate());
            return  r.get(0);
        }
        else {
            List<Room> r = bookingRepository.getRoomClubroomsWithJharokha(bookingRequest.getHotelId(),bookingRequest.getCheckInDate(), bookingRequest.getCheckOutDate());
            return r.get(0);
        }
    }
    public int getAvail(LocalDate checkInDate, LocalDate checkOutDate, String typeName, int hotelId, String pEmail) {
        // Fetch customer by email
        // Check availability based on room type
        switch (typeName) {
            case "deluxe":
                return bookingRepository.getDeluxe(hotelId,checkInDate, checkOutDate) ;
            case "super_deluxe":
                return bookingRepository.getSuperDeluxe(hotelId,checkInDate, checkOutDate) ;
            case "imperial":
                return bookingRepository.getImperial(hotelId,checkInDate, checkOutDate) ;
            case "cottage":
                return bookingRepository.getCottage(hotelId,checkInDate, checkOutDate) ;
            default:
                return bookingRepository.getClubroomsWithJharokha(hotelId,checkInDate, checkOutDate);
        }
    }

    public boolean check(UUID roomReqId,LocalDate CheckIn,LocalDate CheckOut) {
        return  bookingRepository.check(roomReqId,CheckIn,CheckOut)>0;
    }
    public List<GetBooking> getGetBookings(UUID customerID) {
        List<GetBooking> gb = bookingRepository.getBooking(customerID);

        return gb;
    }

    public boolean checkuser(UUID userId) {
        return bookingRepository.checkuser(userId)+serviceRequestRepository.checkuser(userId)>0;
    }
//
//    public GetBooking getGetBooking(UUID bookingID) {
//        List<ServicesProvided> sp = servicesProvidedRepository.getServicesByBookingID(bookingID);
//        List<String> services = new ArrayList<String>();
//        Booking b = getBooking(bookingID);
//        BookRoom bk = bookRoomRepository.getBookRoomByBookingID(b.getBookingID());
//        Room room = roomRepository.getRoomByID(bk.getRoomID());
//        RoomType rt = roomTypeRepository.getRoomTypeByID(room.getRoomCodeID());
//        int cost = room.getCost();
//        String roomNo = room.getRoomNo();
//        Date startDate = b.getCheckInDate();
//        Date endDate = b.getCheckOutDate();
//
//        long timeDifferenceInMillis = endDate.getTime() - startDate.getTime();
//        long daysDifference = timeDifferenceInMillis / (1000 * 60 * 60 * 24);
//        int daysDifferenceAsInt = (int) daysDifference;
//
//        int price = cost*daysDifferenceAsInt;
//        for(int j=0; j<sp.size(); j++) {
//            Services s = serviceRepository.getServiceByID(sp.get(j).getServiceID());
//            String serviceName = s.getName();
//            price += s.getCost();
//            services.add(serviceName);
//        }
//        GetBooking gb = new GetBooking(bookingID, b.getCheckInDate(), b.getCheckOutDate(), b.getNoOfGuests(), services, price, roomNo, rt.getTypeName());
//        return gb;
//    }

//    public Booking getBooking(UUID bookingID) {
//        return bookingRepository.getBooking(bookingID);
//    }
//
//    public int deleteBookRoom(UUID bookingID) {
//        return bookRoomRepository.deleteBookRoom(bookingID);
//    }
//
//    public int deleteBooking(UUID bookingID) {
//        return bookingRepository.deleteBooking(bookingID);
//    }
}
