package com.bsuir.server.services.impl;

import com.bsuir.server.entities.Order;
import com.bsuir.server.entities.Status;
import com.bsuir.server.entities.User;
import com.bsuir.server.repositories.OrderRepository;
import com.bsuir.server.repositories.StatusRepository;
import com.bsuir.server.repositories.UserRepository;
import com.bsuir.server.repositories.exception.RepositoryException;
import com.bsuir.server.repositories.impl.OrderJpaRepository;
import com.bsuir.server.repositories.impl.StatusJpaRepository;
import com.bsuir.server.repositories.impl.UserJpaRepository;
import com.bsuir.server.services.SalesmanService;
import com.bsuir.server.services.exception.ServiceException;
import com.bsuir.server.util.email.EmailSender;

public class SalesmanServiceImpl implements SalesmanService {

    private static final SalesmanServiceImpl INSTANCE = new SalesmanServiceImpl();

    public static SalesmanServiceImpl getInstance() {
        return INSTANCE;
    }

    private SalesmanServiceImpl() {
    }
    private final OrderRepository orderRepository = OrderJpaRepository.getInstance();

    private final StatusRepository statusRepository = StatusJpaRepository.getInstance();

    private final UserRepository userRepository = UserJpaRepository.getInstance();
    @Override
    public void changeOrderStatus(int orderId, int statusId, int userId) throws ServiceException {
        try {
            Order order = orderRepository.get(orderId);
            Status orderStatus = statusRepository.get(statusId);
            order.setOrderStatus(orderStatus);
            orderRepository.update(order);
            User user = userRepository.get(userId);
            String email = user.getEmail();
            if (statusId==3){
            EmailSender.getInstance().sendEmail(
                    email,
                    "Статус вашей заявки",
                     "Заявка одобрена"
            );
            }
            else if(statusId==4){
                EmailSender.getInstance().sendEmail(
                        email,
                        "Статус вашей заявки",
                        "Заявка отклонена"
                );
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
