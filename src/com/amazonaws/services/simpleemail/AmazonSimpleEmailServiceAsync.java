package com.amazonaws.services.simpleemail;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.simpleemail.model.DeleteIdentityRequest;
import com.amazonaws.services.simpleemail.model.DeleteIdentityResult;
import com.amazonaws.services.simpleemail.model.DeleteVerifiedEmailAddressRequest;
import com.amazonaws.services.simpleemail.model.GetIdentityDkimAttributesRequest;
import com.amazonaws.services.simpleemail.model.GetIdentityDkimAttributesResult;
import com.amazonaws.services.simpleemail.model.GetIdentityNotificationAttributesRequest;
import com.amazonaws.services.simpleemail.model.GetIdentityNotificationAttributesResult;
import com.amazonaws.services.simpleemail.model.GetIdentityVerificationAttributesRequest;
import com.amazonaws.services.simpleemail.model.GetIdentityVerificationAttributesResult;
import com.amazonaws.services.simpleemail.model.GetSendQuotaRequest;
import com.amazonaws.services.simpleemail.model.GetSendQuotaResult;
import com.amazonaws.services.simpleemail.model.GetSendStatisticsRequest;
import com.amazonaws.services.simpleemail.model.GetSendStatisticsResult;
import com.amazonaws.services.simpleemail.model.ListIdentitiesRequest;
import com.amazonaws.services.simpleemail.model.ListIdentitiesResult;
import com.amazonaws.services.simpleemail.model.ListVerifiedEmailAddressesRequest;
import com.amazonaws.services.simpleemail.model.ListVerifiedEmailAddressesResult;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;
import com.amazonaws.services.simpleemail.model.SendRawEmailResult;
import com.amazonaws.services.simpleemail.model.SetIdentityDkimEnabledRequest;
import com.amazonaws.services.simpleemail.model.SetIdentityDkimEnabledResult;
import com.amazonaws.services.simpleemail.model.SetIdentityFeedbackForwardingEnabledRequest;
import com.amazonaws.services.simpleemail.model.SetIdentityFeedbackForwardingEnabledResult;
import com.amazonaws.services.simpleemail.model.SetIdentityNotificationTopicRequest;
import com.amazonaws.services.simpleemail.model.SetIdentityNotificationTopicResult;
import com.amazonaws.services.simpleemail.model.VerifyDomainDkimRequest;
import com.amazonaws.services.simpleemail.model.VerifyDomainDkimResult;
import com.amazonaws.services.simpleemail.model.VerifyDomainIdentityRequest;
import com.amazonaws.services.simpleemail.model.VerifyDomainIdentityResult;
import com.amazonaws.services.simpleemail.model.VerifyEmailAddressRequest;
import com.amazonaws.services.simpleemail.model.VerifyEmailIdentityRequest;
import com.amazonaws.services.simpleemail.model.VerifyEmailIdentityResult;
import java.util.concurrent.Future;

public interface AmazonSimpleEmailServiceAsync extends AmazonSimpleEmailService {
    Future<DeleteIdentityResult> deleteIdentityAsync(DeleteIdentityRequest deleteIdentityRequest) throws AmazonServiceException, AmazonClientException;

    Future<DeleteIdentityResult> deleteIdentityAsync(DeleteIdentityRequest deleteIdentityRequest, AsyncHandler<DeleteIdentityRequest, DeleteIdentityResult> asyncHandler) throws AmazonServiceException, AmazonClientException;

    Future<Void> deleteVerifiedEmailAddressAsync(DeleteVerifiedEmailAddressRequest deleteVerifiedEmailAddressRequest) throws AmazonServiceException, AmazonClientException;

    Future<Void> deleteVerifiedEmailAddressAsync(DeleteVerifiedEmailAddressRequest deleteVerifiedEmailAddressRequest, AsyncHandler<DeleteVerifiedEmailAddressRequest, Void> asyncHandler) throws AmazonServiceException, AmazonClientException;

    Future<GetIdentityDkimAttributesResult> getIdentityDkimAttributesAsync(GetIdentityDkimAttributesRequest getIdentityDkimAttributesRequest) throws AmazonServiceException, AmazonClientException;

    Future<GetIdentityDkimAttributesResult> getIdentityDkimAttributesAsync(GetIdentityDkimAttributesRequest getIdentityDkimAttributesRequest, AsyncHandler<GetIdentityDkimAttributesRequest, GetIdentityDkimAttributesResult> asyncHandler) throws AmazonServiceException, AmazonClientException;

    Future<GetIdentityNotificationAttributesResult> getIdentityNotificationAttributesAsync(GetIdentityNotificationAttributesRequest getIdentityNotificationAttributesRequest) throws AmazonServiceException, AmazonClientException;

    Future<GetIdentityNotificationAttributesResult> getIdentityNotificationAttributesAsync(GetIdentityNotificationAttributesRequest getIdentityNotificationAttributesRequest, AsyncHandler<GetIdentityNotificationAttributesRequest, GetIdentityNotificationAttributesResult> asyncHandler) throws AmazonServiceException, AmazonClientException;

    Future<GetIdentityVerificationAttributesResult> getIdentityVerificationAttributesAsync(GetIdentityVerificationAttributesRequest getIdentityVerificationAttributesRequest) throws AmazonServiceException, AmazonClientException;

    Future<GetIdentityVerificationAttributesResult> getIdentityVerificationAttributesAsync(GetIdentityVerificationAttributesRequest getIdentityVerificationAttributesRequest, AsyncHandler<GetIdentityVerificationAttributesRequest, GetIdentityVerificationAttributesResult> asyncHandler) throws AmazonServiceException, AmazonClientException;

    Future<GetSendQuotaResult> getSendQuotaAsync(GetSendQuotaRequest getSendQuotaRequest) throws AmazonServiceException, AmazonClientException;

    Future<GetSendQuotaResult> getSendQuotaAsync(GetSendQuotaRequest getSendQuotaRequest, AsyncHandler<GetSendQuotaRequest, GetSendQuotaResult> asyncHandler) throws AmazonServiceException, AmazonClientException;

    Future<GetSendStatisticsResult> getSendStatisticsAsync(GetSendStatisticsRequest getSendStatisticsRequest) throws AmazonServiceException, AmazonClientException;

    Future<GetSendStatisticsResult> getSendStatisticsAsync(GetSendStatisticsRequest getSendStatisticsRequest, AsyncHandler<GetSendStatisticsRequest, GetSendStatisticsResult> asyncHandler) throws AmazonServiceException, AmazonClientException;

    Future<ListIdentitiesResult> listIdentitiesAsync(ListIdentitiesRequest listIdentitiesRequest) throws AmazonServiceException, AmazonClientException;

    Future<ListIdentitiesResult> listIdentitiesAsync(ListIdentitiesRequest listIdentitiesRequest, AsyncHandler<ListIdentitiesRequest, ListIdentitiesResult> asyncHandler) throws AmazonServiceException, AmazonClientException;

    Future<ListVerifiedEmailAddressesResult> listVerifiedEmailAddressesAsync(ListVerifiedEmailAddressesRequest listVerifiedEmailAddressesRequest) throws AmazonServiceException, AmazonClientException;

    Future<ListVerifiedEmailAddressesResult> listVerifiedEmailAddressesAsync(ListVerifiedEmailAddressesRequest listVerifiedEmailAddressesRequest, AsyncHandler<ListVerifiedEmailAddressesRequest, ListVerifiedEmailAddressesResult> asyncHandler) throws AmazonServiceException, AmazonClientException;

    Future<SendEmailResult> sendEmailAsync(SendEmailRequest sendEmailRequest) throws AmazonServiceException, AmazonClientException;

    Future<SendEmailResult> sendEmailAsync(SendEmailRequest sendEmailRequest, AsyncHandler<SendEmailRequest, SendEmailResult> asyncHandler) throws AmazonServiceException, AmazonClientException;

    Future<SendRawEmailResult> sendRawEmailAsync(SendRawEmailRequest sendRawEmailRequest) throws AmazonServiceException, AmazonClientException;

    Future<SendRawEmailResult> sendRawEmailAsync(SendRawEmailRequest sendRawEmailRequest, AsyncHandler<SendRawEmailRequest, SendRawEmailResult> asyncHandler) throws AmazonServiceException, AmazonClientException;

    Future<SetIdentityDkimEnabledResult> setIdentityDkimEnabledAsync(SetIdentityDkimEnabledRequest setIdentityDkimEnabledRequest) throws AmazonServiceException, AmazonClientException;

    Future<SetIdentityDkimEnabledResult> setIdentityDkimEnabledAsync(SetIdentityDkimEnabledRequest setIdentityDkimEnabledRequest, AsyncHandler<SetIdentityDkimEnabledRequest, SetIdentityDkimEnabledResult> asyncHandler) throws AmazonServiceException, AmazonClientException;

    Future<SetIdentityFeedbackForwardingEnabledResult> setIdentityFeedbackForwardingEnabledAsync(SetIdentityFeedbackForwardingEnabledRequest setIdentityFeedbackForwardingEnabledRequest) throws AmazonServiceException, AmazonClientException;

    Future<SetIdentityFeedbackForwardingEnabledResult> setIdentityFeedbackForwardingEnabledAsync(SetIdentityFeedbackForwardingEnabledRequest setIdentityFeedbackForwardingEnabledRequest, AsyncHandler<SetIdentityFeedbackForwardingEnabledRequest, SetIdentityFeedbackForwardingEnabledResult> asyncHandler) throws AmazonServiceException, AmazonClientException;

    Future<SetIdentityNotificationTopicResult> setIdentityNotificationTopicAsync(SetIdentityNotificationTopicRequest setIdentityNotificationTopicRequest) throws AmazonServiceException, AmazonClientException;

    Future<SetIdentityNotificationTopicResult> setIdentityNotificationTopicAsync(SetIdentityNotificationTopicRequest setIdentityNotificationTopicRequest, AsyncHandler<SetIdentityNotificationTopicRequest, SetIdentityNotificationTopicResult> asyncHandler) throws AmazonServiceException, AmazonClientException;

    Future<VerifyDomainDkimResult> verifyDomainDkimAsync(VerifyDomainDkimRequest verifyDomainDkimRequest) throws AmazonServiceException, AmazonClientException;

    Future<VerifyDomainDkimResult> verifyDomainDkimAsync(VerifyDomainDkimRequest verifyDomainDkimRequest, AsyncHandler<VerifyDomainDkimRequest, VerifyDomainDkimResult> asyncHandler) throws AmazonServiceException, AmazonClientException;

    Future<VerifyDomainIdentityResult> verifyDomainIdentityAsync(VerifyDomainIdentityRequest verifyDomainIdentityRequest) throws AmazonServiceException, AmazonClientException;

    Future<VerifyDomainIdentityResult> verifyDomainIdentityAsync(VerifyDomainIdentityRequest verifyDomainIdentityRequest, AsyncHandler<VerifyDomainIdentityRequest, VerifyDomainIdentityResult> asyncHandler) throws AmazonServiceException, AmazonClientException;

    Future<Void> verifyEmailAddressAsync(VerifyEmailAddressRequest verifyEmailAddressRequest) throws AmazonServiceException, AmazonClientException;

    Future<Void> verifyEmailAddressAsync(VerifyEmailAddressRequest verifyEmailAddressRequest, AsyncHandler<VerifyEmailAddressRequest, Void> asyncHandler) throws AmazonServiceException, AmazonClientException;

    Future<VerifyEmailIdentityResult> verifyEmailIdentityAsync(VerifyEmailIdentityRequest verifyEmailIdentityRequest) throws AmazonServiceException, AmazonClientException;

    Future<VerifyEmailIdentityResult> verifyEmailIdentityAsync(VerifyEmailIdentityRequest verifyEmailIdentityRequest, AsyncHandler<VerifyEmailIdentityRequest, VerifyEmailIdentityResult> asyncHandler) throws AmazonServiceException, AmazonClientException;
}
