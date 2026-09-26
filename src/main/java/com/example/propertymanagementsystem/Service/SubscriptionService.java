package com.example.propertymanagementsystem.Service;

import com.example.propertymanagementsystem.Model.Subscription;
import com.example.propertymanagementsystem.Repository.SubscriptionRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    // Get All Subscriptions
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    // Get Subscription By ID
    public Subscription getSubscriptionById(Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId).orElse(null);
    }

    // Add Subscription
    public Subscription addSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    // Update Subscription
    public Subscription updateSubscription(
            Long subscriptionId,
            Subscription subscription) {

        Subscription oldSubscription =
                subscriptionRepository.findById(subscriptionId).orElse(null);

        if (oldSubscription == null) {
            return null;
        }

        oldSubscription.setOfficeId(subscription.getOfficeId());
        oldSubscription.setPlan(subscription.getPlan());
        oldSubscription.setStartDate(subscription.getStartDate());
        oldSubscription.setEndDate(subscription.getEndDate());
        oldSubscription.setPrice(subscription.getPrice());
        oldSubscription.setStatus(subscription.getStatus());

        return subscriptionRepository.save(oldSubscription);
    }

    // Delete Subscription
    public boolean deleteSubscription(Long subscriptionId) {

        Subscription oldSubscription = subscriptionRepository.findById(subscriptionId).orElse(null);

        if (oldSubscription == null) {
            return false;
        }

        subscriptionRepository.delete(oldSubscription);
        return true;
    }
}
