package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PACKAGE;

@RequiredArgsConstructor(access = PACKAGE)
class GetPaymentUseCaseAdapter implements GetPaymentUseCase {

    private final PaymentsMapper mapper;
    private final GetPaymentService getPaymentService;

    @Override
    public Payment getById(String id) {
        return mapper.toModel(getPaymentService.getById(id));
    }

}
