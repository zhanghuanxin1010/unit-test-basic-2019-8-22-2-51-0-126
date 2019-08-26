package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
    	Project project=new Project(ProjectType.INTERNAL, "text");
        // when
    	ExpenseType result=ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then()
    	assertEquals(ExpenseType.INTERNAL_PROJECT_EXPENSE, result);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
    	Project project=new Project(ProjectType.EXTERNAL, "Project A");
    	
        // when
    	ExpenseType resultA=ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
    	assertEquals(ExpenseType.EXPENSE_TYPE_A, resultA);
    	
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
    	// given
    	Project project=new Project(ProjectType.EXTERNAL, "Project B");
    	
        // when
    	ExpenseType resultB=ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
    	assertEquals(ExpenseType.EXPENSE_TYPE_B, resultB);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        // given
       Project project=new Project(ProjectType.EXTERNAL, "Project C");
    	
        // when
    	ExpenseType resultC=ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
    	assertEquals(ExpenseType.OTHER_EXPENSE, resultC);
    	
    	
      
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() {
        // given
    	  Project project=new Project(ProjectType.UNEXPECTED_PROJECT_TYPE, "T");
        // when
    	  UnexpectedProjectTypeException expection=assertThrows(UnexpectedProjectTypeException.class, 
    			  ()->{ExpenseService.getExpenseCodeByProjectTypeAndName(project);});
        // then
    	  assertEquals("You enter invalid project type", expection.getMessage());
    }
}