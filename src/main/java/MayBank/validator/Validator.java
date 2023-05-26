//package MayBank.Model.validator;
//
//import MayBank.Model.model.AppModel;
//import id.co.asyst.commons.core.payload.BaseParameter;
//import id.co.asyst.commons.core.validator.BaseValidator;
//import id.co.asyst.commons.core.validator.ValidatorType;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Validator extends BaseValidator<AppModel> {
//    public void validate(BaseParameter<AppModel> parameter) {
//        validate(parameter, AppModel.class);
//    }
//
//    public void validate(AppModel databaseModel, String type) {
//        if (ValidatorType.CREATE.equals(type)) {
//            onCreate(databaseModel);
//        } else if (ValidatorType.UPDATE.equals(type)) {
//            onUpdate(databaseModel);
//        } else if (ValidatorType.DELETE.equals(type)) {
//            onDelete(databaseModel);
//        }else if (ValidatorType.RETRIEVEDETAIL.equals(type)){
//            onDelete(databaseModel);
//        }else if(type.equals("login")){
//            onregis(databaseModel);
//        }
//        else if(type.equals("registration")){
//            onregis(databaseModel);
//        }
//        else if(type.equals("edit")){
//            onregis(databaseModel);
//        }
//    }
//
//    private void onCreate(AppModel databaseModel) {
//        notNull(databaseModel, "data");
////        notNull(databaseModel.getUsername(), "username");
////        notBlank(databaseModel.getUsername(), "username");
////        notNull(databaseModel.getPassword(), "password");
////        notBlank(databaseModel.getPassword(), "password");
////        isMax(databaseModel.getUsername(), 25, "username");
////        isMax(databaseModel.getPassword(), 25, "password");
//
//    }
//
//    private void onUpdate(AppModel databaseModel) {
//        onCreate(databaseModel);
//    }
//
//    private void onDelete(AppModel databaseModel) {
//        notNull(databaseModel, "data");
////        notNull(databaseModel.getUsername(), "username");
////        notBlank(databaseModel.getUsername(), "username");
//    }
//
//    private void onregis(AppModel databaseModel) {
//        notNull(databaseModel, "data");
////        notNull(databaseModel.getUsername(), "username");
////        notBlank(databaseModel.getUsername(), "username");
////        notNull(databaseModel.getPassword(), "password");
////        notBlank(databaseModel.getPassword(), "password");
////        isMax(databaseModel.getUsername(), 25, "username");
////        isMax(databaseModel.getPassword(), 25, "password");
//
//    }
//
//
//
//}
