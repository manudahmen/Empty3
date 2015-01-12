/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.script;

public interface Run {
	public void evaluateExpression(Expression expression);
	public void executeInstruction(Instruction instruction);
	public void getVariableValue(Variable variable, Value value);
	public void loopSubCode(SubCode subcode);
	public void runCode(Code code);
	public void setVariableValue(Variable variable, Value value);
	
}
