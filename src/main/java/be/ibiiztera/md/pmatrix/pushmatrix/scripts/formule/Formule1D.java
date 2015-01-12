/*

    Vous êtes libre de :

*/
package be.ibiiztera.md.pmatrix.pushmatrix.scripts.formule;

import java.util.ArrayList;

public class Formule1D {
	public abstract class Atome {
		protected Object value = new Double(0);

		public abstract Double evaluer();

		public String getSymbole() {
			return value.toString();
		}

		public Class<? extends Object> getType() {
			return value == null ? null : value.getClass();
		}
	}

	public class Expression extends Operateur {
	}

	public class FCT_abs extends Fonction
	{
		@Override
		public Double evaluer() {
			return Math.abs(get(0).evaluer());
		}
		
	}

	public abstract class Fonction extends Operateur {
		protected int nbrOperandes;
		public abstract Double evaluer();
	}

	public class Nombre extends Atome {
		@Override
		public Double evaluer() {
			return null;
		}
		public double getValue() {
			return (Double) value;
		}
		public void setValue(Double v)
		{
			value = v;
		}
	}

	public class Operateur extends Atome {
		public final int type_ibi = 0;
		public final int type_add = 1;
		public final int type_sub = 2;
		public final int type_mul = 3;
		public final int type_div = 4;
		public final int type_exp = 5;
		private int type_op = 0;
		protected java.util.List<Expression> operandes = new ArrayList<Formule1D.Expression>();

		public boolean add(Expression e) {
			return operandes.add(e);
		}

		public void clear() {
			operandes.clear();
		}

		@Override
		public Double evaluer() {
			switch (type_op) {
			case type_ibi:
				if (size() == 1)
					return operandes.get(0).evaluer();
				break;
			case type_add:
				if (size() > 1) {
					double sum = 0;
					for (Expression ex : liste())
						sum += ex.evaluer();
					return sum;
				}
				break;

			case type_sub:
				if (size() > 1) {
					double sum = 0;
					int i = 0;
					for (Expression ex : liste()) {
						if (i == 0)
							sum += ex.evaluer();
						else
							sum -= ex.evaluer();
						i++;
					}
					return sum;
				}
				break;
			case type_mul:
				if (size() > 1) {
					double sum = 1;
					for (Expression ex : liste())
						sum *= ex.evaluer();
							return sum;
				}
			case type_div:
				if (size() > 1) {
					double sum = 0;
					int i = 0;
					for (Expression ex : liste()) {
						if (i == 0)
							sum += ex.evaluer();
						else
							sum /= ex.evaluer();
						i++;
					}
					return sum;
				}
				break;
			case type_exp:
				if(size()>1)
				{
					double exp = 1;
					exp = operandes.get(0).evaluer();
					for(int i=1; i<size(); i++)
						exp = Math.pow(exp, get(i).evaluer());
				}
				
			}
			
			return 0.0;
		}

		public Expression get(int index) {
			return operandes.get(index);
		}

		public int getTypeOP() {
			return type_op;
		}

		public boolean isEmpty() {
			return operandes.isEmpty();
		}

		public Iterable<Expression> liste() {
			return operandes;
		}

		public Expression remove(int index) {
			return operandes.remove(index);
		}

		public boolean remove(Object o) {
			return operandes.remove(o);
		}

		public void setTypeOP(int type) {
			this.type_op = type;
		}

		public int size() {
			return operandes.size();
		}

	}
	public class Variable extends Atome {
		@Override
		public Double evaluer() {
			return (Double) value;
		}

		public void setValue(double v) {
			value = v;
		}
	}
}
